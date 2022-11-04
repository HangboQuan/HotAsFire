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
  >使用synchronized(非this对象)，不建议锁字符串，因为在jvm中String常量池缓存的功能, String str1 = "a", String str2 = "b"  这样来看 str1 == str2 是true
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
6.volatile和synchronized的区别和使用情况  
