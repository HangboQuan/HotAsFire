1.非线程安全是怎么出现的？哪些情况会导致线程安全？  
>非线程安全：多个线程对同一个对象的实例变量并发进行访问，由于某些语句非原子性(i ++)，就会出现脏读，即取到的数据是被别的线程更改过的  
线程安全：多个线程对同一个对象的实例变量并发进行访问，获取变量的值是经过同步处理的，不会出现脏读的情况  

多个线程并发访问同一个公有对象，可能导致线程安全问题
```java
public class Chapter02_A1 {
	
	private int num;
	synchronized public void addI(String username) {
		try{
			if("a".equalsIgnoreCase(username)) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);
			} else {
				num = 200;
				System.out.println("b set over");
			}
			System.out.println("username = " + username + " num = " + num);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Chapter02_A1_01 extends Thread {
	
	private Chapter02_A1 chapter02_a1;
	
	public Chapter02_A1_01(Chapter02_A1 chapter02_a1) {
		super.run();
		this.chapter02_a1 = chapter02_a1;
	}
	
	@Override
	public void run() {
		chapter02_a1.addI("a");
	}
}

class Chapter02_A1_02 extends Thread {

	private Chapter02_A1 chapter02_a1;
	
	public Chapter02_A1_02(Chapter02_A1 chapter02_a1) {
		super.run();
		this.chapter02_a1 = chapter02_a1;
	}
	
	@Override
	public void run() {
		chapter02_a1.addI("b");
	}
}

class Chapter02_A1_03 {
	
	public static void main(String[] args){
		
		/**
		 * result:
		 * a set over
		 * b set over
		 * username = b num = 200
		 * username = a num = 200
		 *
		 * 如果将共有对象的访问权限改为public,同样会出现非线程安全问题
		 *
		 * 两个线程同时访问一个没有同步的方法，如果两个线程同时操作业务对象中的
		 * 私有实例变量，则可能出现非线程安全问题
		 *
		 * 可以在非同步方法加synchronized
		 * result:
		 * a set over
		 * username = a num = 100
		 * b set over
		 * username = b num = 200
		 */
		
		/**
		 * 两个线程共用同一个对象，不加同步处理的话，会出现非线程安全问题
		 *
		 * 两个线程去争抢同一把锁，因此造成的
		 */
		/*Chapter02_A1 chapter02_a1 = new Chapter02_A1();
		
		Chapter02_A1_01 chapter02_a1_01 = new Chapter02_A1_01(chapter02_a1);
		chapter02_a1_01.start();
		
		Chapter02_A1_02 chapter02_a1_02 = new Chapter02_A1_02(chapter02_a1);
		chapter02_a1_02.start();*/
		
		
		/**
		 *
		 * 这段代码中创建了两个实例对象，即使用synchronized锁住同步方法，但是从执行结果来看，还是异步执行的
		 * 多个线程访问多个对象，JVM会创建多个锁，synchronized取得的锁
		 * 都是对象锁，哪个线程先执行带synchronized关键字的方法，线程
		 * 就持有该方法所属对象的锁Lock
		 * result:
		 * a set over
		 * b set over
		 * username = b num = 200
		 * username = a num = 100
		 */
		Chapter02_A1 chapter02_a1 = new Chapter02_A1();
		Chapter02_A1 chapter02_a2 = new Chapter02_A1();
		
		Chapter02_A1_01 chapter02_a1_01 = new Chapter02_A1_01(chapter02_a1);
		Chapter02_A1_02 chapter02_a1_02 = new Chapter02_A1_02(chapter02_a2);
		
		chapter02_a1_01.start();
		chapter02_a1_02.start();
	}	
}
```
通过synchronized关键字来解决非线程安全问题，来实现同步化，使当前运行的线程阻塞  
2.synchronized锁方法  
2.1 synchronzied锁方法本质上是锁的是调用该同步方法的对象，通过该对象创建多个线程同时来访问该同步方法的时候，就会出现锁竞争 ，线程阻塞  
2.2 如果在一个类中，synchronized锁了一个同步方法，而其他方法都是非synchronized方法，此时两个线程来调用的时候? 是异步调用还是同步调用?  
```java
public class Chapter02_A3 {
	
	synchronized public void methodA() {
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void methodB() {
		
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("END " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}
}

class Chapter02_A3_01 extends Thread{
	
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_01(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodA();
	}
	
}

class Chapter02_A3_02 extends Thread {
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_02(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodB();
	}
}

class Chapter02_A3_03 {

	/**
	 * ThreadA调用methodA, 虽然ThreadA持有了object对象锁，但是ThreadB还是可以异步的调用非synchronized类型方法？ 不是说synchronized锁住的
	 * object对象，但是为什么同一个实例对象调用非同步方法的时候，还是执行的异步，那么这样的话会不会带来脏数据呢？答案是肯定的，会带来脏读的问题
	 *
	 * ThreadA调用加synchronized的methodA, 其实synchronized就持有了实例对象来调用方法的对象锁，如果别的线程也想调用方法methodA,只能等到ThreadA
	 * 释放对象锁之后，才可以; ThreadA调用加synchronized的methodA，ThreadB调用非synchronized的methodB, 即使使用的同一个实例对象，也互不影响
	 * 异步执行
	 *
	 * 如果methodA和methodB都给这个方法加了synchronized，那么同一个实例对象访问的时候，就是同步执行的
	 * result:
	 * currentThreadName :ThreadA
	 * currentThreadName :ThreadB
	 * end 5000
	 * END 5000
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_A3 chapter02_a3 = new Chapter02_A3();
		Chapter02_A3_01 chapter02_a3_01 = new Chapter02_A3_01(chapter02_a3);
		Chapter02_A3_02 chapter02_a3_02 = new Chapter02_A3_02(chapter02_a3);
		
		chapter02_a3_01.setName("ThreadA");
		chapter02_a3_02.setName("ThreadB");
		
		chapter02_a3_01.start();
		chapter02_a3_02.start();
	}
}****
```
2.3 锁重入? 在继承的情况下子类能否调用父类对象的锁，能不能继续支持可重入?
```java
public class Chapter02_A6 {

	public int i = 10;
	synchronized public void operateIParentMethod() {
		try {
			i --;
			System.out.println("parent print i = " + i);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter02_A6_01 extends Chapter02_A6 {

	synchronized public void operateISubMethod() {
		try {
			while (i > 0) {
				i --;
				System.out.println("sub parent i = " + i);
				this.operateIParentMethod();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_A6_02 extends Thread {

	@Override
	public void run() {
		Chapter02_A6_01 chapter02_a6_01 = new Chapter02_A6_01();

		chapter02_a6_01.operateISubMethod();
	}


	public static void main(String[] args) {

		/**
		 * 可重入锁也支持父子类继承的环境，子类是可以通过'可重入锁'调用父类的同步方法
		 * result:
		 * sub parent i = 9
		 * parent print i = 8
		 * sub parent i = 7
		 * parent print i = 6
		 * sub parent i = 5
		 * parent print i = 4
		 * sub parent i = 3
		 * parent print i = 2
		 * sub parent i = 1
		 * parent print i = 0
		 */

		Chapter02_A6_02 chapter02_a6_02 = new Chapter02_A6_02();
		chapter02_a6_02.start();
	}
}
```
2.4 出现异常后，锁会释放吗?
```java
public class Chapter02_A7 {
	synchronized public void testMethod() {
		if (Thread.currentThread().getName().equals("ThreadA")) {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + ": begin = " + System.currentTimeMillis());
			int i = 1;

			while(i < 500000) {
				if(Integer.parseInt(("" + Math.random()).substring(2, 6)) > 9998) {
					System.out.println("ThreadName = " + Thread.currentThread().getName() + ": exception = " + System.currentTimeMillis());
					Integer.parseInt("a");
					i ++;
				}
			}
		} else {
			System.out.println("ThreadName B = " + Thread.currentThread().getName() + ":" + System.currentTimeMillis());
			int i = 10;
			while (i > 0) {
				System.out.println("ThreadName: " + Thread.currentThread().getName() + " : " + i);
				i --;
			}
		}
	}
}

class Chapter02_A7_01 extends Thread {

	private Chapter02_A7 chapter02_a7;


	public Chapter02_A7_01(Chapter02_A7 chapter02_a7) {
		this.chapter02_a7 = chapter02_a7;
	}

	@Override
	public void run() {
		chapter02_a7.testMethod();
	}
}


class Chapter02_A7_02 extends Thread {

	private Chapter02_A7 chapter02_a7;

	public Chapter02_A7_02(Chapter02_A7 chapter02_a7) {

		this.chapter02_a7 = chapter02_a7;
	}


	@Override
	public void run() {
		chapter02_a7.testMethod();
	}
}


class Chapter02_A7_03 {


	public static void main(String[] args) {


		/**
		 * 从结果验证来，如果一个线程持有对象锁&&该线程发生异常，书上说的是会抛出异常
		 * 但是实际测试中并没有释放锁？这里的原因给抛异常的Integer.parseInt()使用了try catch所以才看到的是同步的效果，并没有释放锁
		 *
		 *
		 * 当线程持有实例对象的锁之后，当前线程抛出异常时，会释放锁
		 *
		 * result:
		 * ThreadName = ThreadA: begin = 1665916508447
		 * ThreadName = ThreadA: exception = 1665916508479
		 * Exception in thread "ThreadA" java.lang.NumberFormatException: For input string: "a"
		 * 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:68)
		 * 	at java.base/java.lang.Integer.parseInt(Integer.java:658)
		 * 	at java.base/java.lang.Integer.parseInt(Integer.java:776)
		 * 	at chapter02.Chapter02_A7.testMethod(Chapter02_A7.java:16)
		 * 	at chapter02.Chapter02_A7_01.run(Chapter02_A7.java:42)
		 * ThreadName B = ThreadB:1665916508947
		 * ThreadName: ThreadB : 10
		 * ThreadName: ThreadB : 9
		 * ThreadName: ThreadB : 8
		 * ThreadName: ThreadB : 7
		 * ThreadName: ThreadB : 6
		 * ThreadName: ThreadB : 5
		 * ThreadName: ThreadB : 4
		 * ThreadName: ThreadB : 3
		 * ThreadName: ThreadB : 2
		 * ThreadName: ThreadB : 1
		 */

		try {
			Chapter02_A7 chapter02_a7 = new Chapter02_A7();

			Chapter02_A7_01 chapter02_a7_01 = new Chapter02_A7_01(chapter02_a7);
			chapter02_a7_01.setName("ThreadA");
			chapter02_a7_01.start();

			Thread.sleep(500);
			Chapter02_A7_02 chapter02_a7_02 = new Chapter02_A7_02(chapter02_a7);
			chapter02_a7_02.setName("ThreadB");
			chapter02_a7_02.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
```
2.5 再同步方法中使用synchronized的弊端？
- synchronized锁同步方法，该锁的粒度较粗，如果持有该对象的同步锁获取到该方法的同步锁，那么其他线程就必须等待，直到方法结束或者跑出异常释放锁之后，才能继续抢占锁(假如获得同步锁的对象调用同步方法时，是一个耗时的操作的话，那么别的线程想要抢占锁，却只能阻塞)
```java
package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 上午8:57
 */
public class Chapter02_A9 {

	private String getData1;
	private String getData2;

	synchronized public void doLongTimeTask() {
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "长时间处理任务后从远程返回的值1 threadName = " + Thread.currentThread().getName();
			getData2 = "长时间处理任务后从远程返回的值2 threadName = " + Thread.currentThread().getName();

			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_A9_01 {

	public static long beginTime1;
	public static long endTime1;
	public static long beginTime2;
	public static long endTime2;
}

class Chapter02_A9_02 extends Thread {

	private Chapter02_A9 chapter02_a9;

	public Chapter02_A9_02(Chapter02_A9 chapter02_a9) {
		this.chapter02_a9 = chapter02_a9;
	}

	@Override
	public void run(){
		super.run();
		Chapter02_A9_01.beginTime1 = System.currentTimeMillis();
		chapter02_a9.doLongTimeTask();
		Chapter02_A9_01.endTime1 = System.currentTimeMillis();
	}
}

class Chapter02_A9_03 extends Thread {

	private Chapter02_A9 chapter02_a9;

	public Chapter02_A9_03(Chapter02_A9 chapter02_a9) {
		this.chapter02_a9 = chapter02_a9;
	}

	@Override
	public void run(){
		super.run();
		Chapter02_A9_01.beginTime2 = System.currentTimeMillis();
		chapter02_a9.doLongTimeTask();
		Chapter02_A9_01.endTime2 = System.currentTimeMillis();
	}
}

class Chapter02_A9_04 {

	public static void main(String[] args) {

		/**
		 * synchronized锁同步方法的时候,范围面大一些,如果A线程调用同步方法执行一个长时间的任务,B线程则必须等待较长的时间;
		 *
		 * 那么就可以使用同步块来解决这样的问题
		 * result:
		 * begin task
		 * 长时间处理任务后从远程返回的值1 threadName = Thread-0
		 * 长时间处理任务后从远程返回的值2 threadName = Thread-0
		 * end task
		 * begin task
		 * 长时间处理任务后从远程返回的值1 threadName = Thread-1
		 * 长时间处理任务后从远程返回的值2 threadName = Thread-1
		 * end task
		 * 耗时: 6003
		 *
		 */
		Chapter02_A9 chapter02_a9 = new Chapter02_A9();

		Chapter02_A9_02 chapter02_a9_02 = new Chapter02_A9_02(chapter02_a9);
		chapter02_a9_02.start();

		Chapter02_A9_03 chapter02_a9_03 = new Chapter02_A9_03(chapter02_a9);
		chapter02_a9_03.start();

		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long beginTime = Chapter02_A9_01.beginTime1;
		if(Chapter02_A9_01.beginTime2 < Chapter02_A9_01.beginTime1) {
			beginTime = Chapter02_A9_01.beginTime2;
		}

		long endTime = Chapter02_A9_01.endTime1;
		if(Chapter02_A9_01.endTime2 > Chapter02_A9_01.endTime1) {
			endTime = Chapter02_A9_01.endTime2;
		}

		System.out.println("耗时: " + (endTime - beginTime));

	}
}
```
- 同步不能被继承(貌似一般也没这样用)  
3.synchronized锁class /this/其他对象
- 静态方法锁的synchronized(xxx.class), 普通方法锁的是this
- synchronized(this)和synchronized锁同步方法本质上都是锁住的是 当前方法所处的类的对象，这样做的好处是比同步方法的锁的粒度会更细一点，从而可以避免不必要的线程阻塞
```java
public class Chapter02_B2 {

	public void serviceTask(){
		for(int i = 0; i < 100; i ++ ) {
			System.out.println("no synchronized threadName " + Thread.currentThread().getName() + " value = " + i);
		}

		synchronized (this) {
			for(int i = 0; i < 100; i ++ ) {
				System.out.println("synchronized threadName " + Thread.currentThread().getName() + "value = " + i);
			}
		}
	}
}

class Chapter02_B2_01 extends Thread {

	private Chapter02_B2 chapter02_b2;

	public Chapter02_B2_01(Chapter02_B2 chapter02_b2) {
		this.chapter02_b2 = chapter02_b2;
	}

	@Override
	public void run() {
		chapter02_b2.serviceTask();
	}
}

class Chapter02_B2_02 extends Thread {

	private Chapter02_B2 chapter02_b2;

	public Chapter02_B2_02(Chapter02_B2 chapter02_b2) {
		this.chapter02_b2 = chapter02_b2;
	}

	@Override
	public void run() {
		chapter02_b2.serviceTask();
	}
}

class Chapter02_B2_03 extends Thread {

	/**
	 * 结论: 不加synchronized锁的代码是异步执行的，但是加synchronized的代码是同步执行的
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_B2 chapter02_b2 = new Chapter02_B2();
		Chapter02_B2_01 chapter02_b2_01 = new Chapter02_B2_01(chapter02_b2);
		chapter02_b2_01.setName("ThreadA");
		chapter02_b2_01.start();
		Chapter02_B2_02 chapter02_b2_02 = new Chapter02_B2_02(chapter02_b2);
		chapter02_b2_02.setName("ThreadB");
		chapter02_b2_02.start();
	}

}
```
- 同一个类中包含多个synchronized(this)，多个线程同时去访问不同的方法的时候，仍然是同步的状态
- synchronized不仅可以锁this对象或者xxx.class对象，也可以锁其他对象 如：Object obj = new Object, String str = new String(), List list = new ArrayList<>()
  >优点是：如果一个类中有多个synchronized方法，虽然是同步的执行，但是由于线程之间竞争阻塞，导致影响效率，如果是锁(非this对象)，这样就和其他锁是异步执行的，可以提高执行效率
 ```java
  public class Chapter02_B5 {

	/**
	 * synchronized同步方法:
	 * 1. 对其他synchronized同步方法或者synchronized(this)同步代码块呈阻塞状态
	 * 2. 同一时间只有一个线程可以执行synchronized同步方法
	 *
	 * synchronized(this)同步方法:
	 * 1. 对其他synchronized同步方法或者synchronized(this)同步代码块呈阻塞状态
	 * 2. 同一时间只有一个线程可以执行synchronized(this)同步方法
	 */

	private String username;
	private String password;

	private String anyString = new String();

	public void setUsernameAndPassword(String uparm, String pparm) {
		/**
		 * 这个锁非this对象和锁this　的效果是一样的，都是同步的打印了信息
		 *
		 * 锁this和锁非this的区别是: 如果一个类中synchronized特别多的话，由于锁住了this,该类中的其他代码就必须是同步执行的,耗时较高，影响效率
		 * 如果synchronized(非this)则该代码块的代码和 其他synchronized(this)或者synchronized(其他对象)同步方法是异步执行的，不与其他同步方法争抢锁，
		 * 因此可以提高执行效率
		 *
		 */
		synchronized (anyString){
			try {
				System.out.println("threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				username = uparm;
				Thread.sleep(2000);
				password = pparm;

				System.out.println("threadName = " + Thread.currentThread().getName() + " username = " + username + " password = " + password +
						System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter02_B5_01 extends Thread {

	private Chapter02_B5 chapter02_b5;

	public Chapter02_B5_01(Chapter02_B5 chapter02_b5) {
		this.chapter02_b5 = chapter02_b5;
	}

	@Override
	public void run() {
		chapter02_b5.setUsernameAndPassword("A", "AA");

	}
}

class Chapter02_B5_02 extends Thread {

	private Chapter02_B5 chapter02_b5;

	public Chapter02_B5_02(Chapter02_B5 chapter02_b5) {
		this.chapter02_b5 = chapter02_b5;
	}

	@Override
	public void run() {
		chapter02_b5.setUsernameAndPassword("B", "BB");

	}
}

class Chapter02_B5_03 {

	/**
	 * result:
	 * threadName = ThreadA 1666010728821
	 * threadName = ThreadA username = A password = AA1666010730822
	 * threadName = ThreadB 1666010730822
	 * threadName = ThreadB username = B password = BB1666010732822
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_B5 chapter02_b5 = new Chapter02_B5();

		Chapter02_B5_01 chapter02_b5_01 = new Chapter02_B5_01(chapter02_b5);
		chapter02_b5_01.setName("ThreadA");
		chapter02_b5_01.start();

		Chapter02_B5_02 chapter02_b5_02 = new Chapter02_B5_02(chapter02_b5);
		chapter02_b5_02.setName("ThreadB");
		chapter02_b5_02.start();
	}

}
  ```
  >使用synchronized(非this对象)，不建议锁字符串，因为在jvm中String常量池缓存的功能, String str1 = "a", String str2 = "b"  这样来看 str1 == str2 是true，这样就导致了可能不同的线程去获取锁，但是其实总是获取到的是str1这个字符串这个方法中的锁，另外一个方法总是得不到执行
 - 锁其他对象的时候，如果更改了对象的属性，并不影响线程的同步  
 4.多线程的死锁
 ```java
 public class Chapter02_C8 implements Runnable {

	private String username;
	private Object object1 = new Object();
	private Object object2 = new Object();


	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 不同的线程都在等待不可能释放的锁，从而导致所有的任务都无法继续完成
	 */
	@Override
	public void run() {
		if(username.equals("a")) {
			synchronized (object1) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (object2) {
					System.out.println("按object1 -> object2 代码顺序来执行");
				}
			}

		}

		if(username.equals("b")) {
			synchronized (object2) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (object1) {
					System.out.println("按object2 -> object1 代码顺序执行");
				}
			}


		}
	}
}

class Chapter02_C8_01 extends Thread {

	public static void main(String[] args) {
		try {
			Chapter02_C8 chapter02_c8 = new Chapter02_C8();

			chapter02_c8.setUsername("a");
			Thread thread1 = new Thread(chapter02_c8);
			thread1.start();
			Thread.sleep(10);

			chapter02_c8.setUsername("b");
			Thread thread2 = new Thread(chapter02_c8);
			thread2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
 ```

5.volatile的作用  
volatile是更轻量级的锁，volatile主要来解决之间的可见性问题，出现死循环无法停止
```java
public class Chapter02_D3 implements Runnable {

	//关键字volatile主要作用是使变量在多个线程中可见
	private boolean isContinuePrint = true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}

	public void serviceMethod() {
		int i = 0;
		while(isContinuePrint == true) {
			i ++;
			System.out.println("ThreadName " + Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {
		serviceMethod();
	}
}

class Chapter02_D3_01 {


	/**
	 * stop it, stop thread = main
	 * ThreadName Thread-0 1
	 *
	 * 这个代码运行在服务器上会出现死循环 解决办法是使用volatile关键字
	 *
	 * volatile关键字的作用: 强制从公共堆栈中取得变量的值，而不是从线程私有的数据栈取得变量值
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * main线程处理while循环 导致不能执行后面的代码
		 */
		Chapter02_D3 chapter02_d3 = new Chapter02_D3();

		new Thread(chapter02_d3).start();
		System.out.println("stop it, stop thread = " + Thread.currentThread().getName());
		chapter02_d3.setContinuePrint(false);

	}

}
```
通过volatile关键字，使变量在不同的线程中可见，=> 从对象的私有堆栈的信息 -> 同步到公共堆栈区 -> 每次总是获取最新的变量的消息
```java
public class Chapter02_D4 extends Thread {


	/**
	 * synchronized和volatile的比较
	 *
	 * 1. 关键字volatile是线程同步的轻量级实现，volatile的性能优于synchronized；volatile只能修饰变量，而synchronized可以修饰方法，以及代码块。
	 * 随着版本的迭代，synchronized关键字在执行效率上大幅提升，开发中也更多使用的是synchronized
	 *
	 * 2. 多线程访问volatile不会发生阻塞，synchronized会发生阻塞
	 *
	 * 3. volatile能保证数据的可见性，但不能保证原子性；synchronized能保证数据的原子性，也可以保证数据的可见性
	 *
	 * 4. volatile是解决变量在多个线程之间的可见性；而synchronized是解决多个线程之间的同步性
	 */
	volatile private boolean isRunning = true;

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {

		this.isRunning = isRunning;
	}


	@Override
	public void run() {
		System.out.println("线程进入run方法了");
		while(isRunning == true) {

		}
		System.out.println("线程被停止了！");

	}

}

class Chapter02_D4_01 {

	/**
	 * private boolean isRunning = true, 存在于公共堆栈及线程的私有栈中, JVM被设置为-server时为了线程运行效率，从私有堆栈中获取的isRunning=true
	 *
	 * chapter02_03.setRunning(false)更新的是 公共堆栈的isRunning=false
	 *
	 * 出现死循环的问题就是私有堆栈的值和公共堆栈的值 不同步造成的
	 *
	 * 解决方法就是需要用到volatile 主要作用是当线程访问isRunning，强制从公共堆栈进行取值
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_D4 chapter02_d4 = new Chapter02_D4();
		chapter02_d4.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chapter02_d4.setRunning(false);
		System.out.println("已经被赋值为false");
	}

}
```
- volatile虽然能保证可见性，但是无法保证同步性  
![](https://github.com/HangboQuan/HotAsFire/blob/main/images/javaconcurrent/volatile.jpg)
6.volatile和synchronized的区别和使用情况 
- volatile只保证了可见性，但是无法保证同步性；synchronized保证了同步性，也间接的保证了可见性
```java
public class Chapter02_D5 extends Thread {

	/**
	 * volatile非原子特性
	 *
	 * 用volatile修饰的变量保证了变量在多个线程之间的可见性，但是并不能保证原子性
	 * 用volatile修饰变量在内存中的工作流程
	 * 1. read和load: 从主存中复制变量到当前工作内存
	 * 2. use和assign(分配): 执行代码，改变共享变量的值
	 * 3. store和write: 用工作内存数据刷新主存对应变量的值
	 * 其中load use assign这三步操作是非原子性的 当执行read和load从主存加载变量之后，如果此时主存的数据发生更改，但是在线程工作区变量已经加载了，
	 * 无法同步更新，因此造成数据不同步，即出现非线程安全问题
	 *
	 * 使用volatile修饰的变量，JVM保证从主内存加载到线程工作内存的值是最新的，如果线程1和线程2在执行read和load的时候，发现主存count值是10，那么就会
	 * 加载这个最新的值，主要就是解决线程之间的可见性问题，但是不能保证原子性问题
	 */

	volatile public static int count;

	/**
	 * 在这里如果不加synchronized 则无法保证它的原子性，最终的结果就是小于10000
	 * 加上synchronized 则最终的结果是10000
	 */
	synchronized private static void addCount() {

		for(int i = 0; i < 100; i ++ ) {
			count ++;
		}
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " count = " + count);
	}


	@Override
	public void run() {
		addCount();
	}
}



class Chapter02_D5_01 {

	/**
	 * 开启了100个线程，每个线程做得事情是将公有变量+100，按照同步的结果，最终的结果应该是10000
	 *
	 * 但是实际来看最终执行完的结果 总是小于10000
	 * ThreadName = Thread-2 count = 300
	 * ThreadName = Thread-9 count = 900
	 * ThreadName = Thread-3 count = 700
	 * ThreadName = Thread-6 count = 700
	 * ThreadName = Thread-5 count = 500
	 * ThreadName = Thread-4 count = 400
	 * ThreadName = Thread-0 count = 200
	 * ThreadName = Thread-1 count = 300
	 * ThreadName = Thread-7 count = 800
	 * ThreadName = Thread-11 count = 1185
	 * ThreadName = Thread-10 count = 1085
	 * ThreadName = Thread-8 count = 1085
	 * ThreadName = Thread-12 count = 1285
	 * ThreadName = Thread-13 count = 1385
	 * ThreadName = Thread-14 count = 1485
	 * ThreadName = Thread-15 count = 1585
	 * ThreadName = Thread-16 count = 1685
	 * ThreadName = Thread-19 count = 1786
	 * ThreadName = Thread-20 count = 1985
	 * ThreadName = Thread-22 count = 2285
	 * ThreadName = Thread-17 count = 1885
	 * ThreadName = Thread-18 count = 2285
	 * ThreadName = Thread-21 count = 2085
	 * ThreadName = Thread-23 count = 2385
	 * ThreadName = Thread-25 count = 2585
	 * ThreadName = Thread-24 count = 2485
	 * ThreadName = Thread-26 count = 2685
	 * ThreadName = Thread-27 count = 2785
	 * ThreadName = Thread-29 count = 2885
	 * ThreadName = Thread-30 count = 3085
	 * ThreadName = Thread-28 count = 3085
	 * ThreadName = Thread-35 count = 3585
	 * ThreadName = Thread-31 count = 3385
	 * ThreadName = Thread-34 count = 3485
	 * ThreadName = Thread-33 count = 3285
	 * ThreadName = Thread-32 count = 3185
	 * ThreadName = Thread-36 count = 3685
	 * ThreadName = Thread-38 count = 3885
	 * ThreadName = Thread-37 count = 3885
	 * ThreadName = Thread-39 count = 3985
	 * ThreadName = Thread-40 count = 4085
	 * ThreadName = Thread-41 count = 4198
	 * ThreadName = Thread-42 count = 4198
	 * ThreadName = Thread-43 count = 4298
	 * ThreadName = Thread-44 count = 4498
	 * ThreadName = Thread-46 count = 4498
	 * ThreadName = Thread-47 count = 4598
	 * ThreadName = Thread-45 count = 4698
	 * ThreadName = Thread-48 count = 4798
	 * ThreadName = Thread-50 count = 4998
	 * ThreadName = Thread-51 count = 5098
	 * ThreadName = Thread-49 count = 4998
	 * ThreadName = Thread-52 count = 5198
	 * ThreadName = Thread-53 count = 5298
	 * ThreadName = Thread-54 count = 5398
	 * ThreadName = Thread-55 count = 5498
	 * ThreadName = Thread-56 count = 5598
	 * ThreadName = Thread-57 count = 5698
	 * ThreadName = Thread-58 count = 5798
	 * ThreadName = Thread-60 count = 5898
	 * ThreadName = Thread-61 count = 5998
	 * ThreadName = Thread-59 count = 6098
	 * ThreadName = Thread-62 count = 6198
	 * ThreadName = Thread-63 count = 6298
	 * ThreadName = Thread-64 count = 6398
	 * ThreadName = Thread-65 count = 6498
	 * ThreadName = Thread-66 count = 6598
	 * ThreadName = Thread-67 count = 6698
	 * ThreadName = Thread-68 count = 6798
	 * ThreadName = Thread-70 count = 6998
	 * ThreadName = Thread-69 count = 6898
	 * ThreadName = Thread-71 count = 7098
	 * ThreadName = Thread-72 count = 7198
	 * ThreadName = Thread-73 count = 7298
	 * ThreadName = Thread-74 count = 7398
	 * ThreadName = Thread-75 count = 7498
	 * ThreadName = Thread-76 count = 7598
	 * ThreadName = Thread-77 count = 7698
	 * ThreadName = Thread-78 count = 7798
	 * ThreadName = Thread-79 count = 7898
	 * ThreadName = Thread-80 count = 7998
	 * ThreadName = Thread-81 count = 8098
	 * ThreadName = Thread-82 count = 8198
	 * ThreadName = Thread-84 count = 8398
	 * ThreadName = Thread-83 count = 8298
	 * ThreadName = Thread-85 count = 8569
	 * ThreadName = Thread-86 count = 8669
	 * ThreadName = Thread-88 count = 8769
	 * ThreadName = Thread-87 count = 8669
	 * ThreadName = Thread-89 count = 8869
	 * ThreadName = Thread-90 count = 8969
	 * ThreadName = Thread-91 count = 9069
	 * ThreadName = Thread-92 count = 9169
	 * ThreadName = Thread-93 count = 9269
	 * ThreadName = Thread-94 count = 9369
	 * ThreadName = Thread-95 count = 9469
	 * ThreadName = Thread-96 count = 9569
	 * ThreadName = Thread-97 count = 9669
	 * ThreadName = Thread-98 count = 9769
	 * ThreadName = Thread-99 count = 9869
	 */

	public static void main(String[] args) {

		Chapter02_D5[] chapter02_d5 = new Chapter02_D5[100];

		for(int i = 0; i < 100; i ++ ) {
			chapter02_d5[i] = new Chapter02_D5();
		}

		for(int i = 0; i < 100; i ++ ) {
			chapter02_d5[i].start();
		}
	}


}

```
- 通过AtomicInteger等 Atomic-原子类保证原子性
