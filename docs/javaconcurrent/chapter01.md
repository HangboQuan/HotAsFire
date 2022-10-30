1. 线程和进程的区别?  
  >进程：是操作系统结构的基础，是一次程序的执行，是一个程序及其数据在处理机上顺序执行时所发生的活动，它是系统进行资源分配和调度的一个独立单位  
  线程：是操作系统能够进行运算调度的最小单位，它被包含在进程之中，是进程的实际运作单位，一个正在运行的进程至少有一个线程在运行  
2. 线程的启动？  
  2.1 继承Thread  
  ```java
  public class Chapter01_A2 extends Thread{
	private int i;
	
	public Chapter01_A2(int i) {
		super();
		this.i = i;
	}
	
	@Override
	public void run(){
		System.out.println(i);
	}
	
	// 线程启动顺序和start()执行顺序无关
	public static void main(String[] args){
		Chapter01_A2 a0 = new Chapter01_A2(0);
		Chapter01_A2 a1 = new Chapter01_A2(1);
		Chapter01_A2 a2 = new Chapter01_A2(2);
		Chapter01_A2 a3 = new Chapter01_A2(3);
		Chapter01_A2 a4 = new Chapter01_A2(4);
		Chapter01_A2 a5 = new Chapter01_A2(5);
		Chapter01_A2 a6 = new Chapter01_A2(6);
		
		a0.start();
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		a6.start();
	}
}
```
  2.2 实现Runnable接口  
```java
public class Chapter01_A3 implements Runnable {
	
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName() + "运行中!");
	}

	/**
	 * Thread(Runnable target): Allocates a new Thread object 分配一个新的线程对象
	 * Thread(Runnable target,String name): Allocates a new Thread object
	 */
	
	public static void main(String[] args){
		Runnable runnable = new Chapter01_A3();
		// Thread thread = new Thread(runnable, "ThreadA");
		/**
		 * result: Thread运行中!
		 */
		Thread thread = new Thread(runnable, "Thread");
		thread.start();
		
		Runnable runnableA = new Chapter01_A3_01();
		
		/**
		 * Thread实现了Runnable接口，意味着构造函数Thread(Runnable target)不光可以传入Runnable接口的对象
		 * 还可以传入Thread类的对象 -> 即将一个Thread对象中的run()交由其他线程调用(但是这样做意义何在? 为什么
		 * 自己线程对象不去调自己线程对象对应的run())
		 */
		Thread threadA = new Thread(runnableA, "ThreadA");
		Thread threadB = new Thread(threadA);
		
		/**
		 * result: Thread-0运行结束
		 */
		threadB.start();
	}
}

class Chapter01_A3_01 implements Runnable{
	
	@Override
	public void run(){
		System.out.println(Thread.currentThread().getName() + "运行结束");
	}
}
```
这里只列举了两种启动线程的方式，其实还有实现Callable接口，lambda表达式，线程池等方式来创建线程，但其实本质上都是实现Runnable接口，调动run()来实现的  
3. 线程的常见的几种方法？  
  - isAlive()：判断当前的线程是否处于活动状态  
  - sleep()：在指定的毫秒数让当前正在执行的线程休眠（暂停执行），不会释放锁  
  - yield()： 放弃当前的CPU资源，将让给其他任务去占用CPU执行时间，但是放弃时间不确定，可能刚刚放弃，又立刻获得时间片，不会释放锁  
  - join()：一种特殊的wait(), waits for this thread to die.
  - wait()： 使当前线程等待，直到另一个线程为此对象调用notify()或者notifyAll()，会释放锁 
  - notify()： 唤醒一个持有该对象锁且处于等待状态的线程，不释放锁  
  - notifyAll()：唤醒所有持有该对象锁且处于等待状态的线程，不释放锁  
4. 线程的状态：new(新建)、runnable(就绪)、running(运行)、blocked(阻塞)、dead(死亡)  
![](https://github.com/HangboQuan/HotAsFire/blob/main/images/javaconcurrent/Thread-state.jpg)

5. 如何使线程暂停？ 
  - stop()：This method is inherently unsafe. Stopping a thread with Thread.stop causes it to unlock all of the monitots that it has locked. 这个方法本质上是不安全的，调用这个方法会抛出java.lang.ThreadDeath异常，停止线程会导致它解锁所有已锁定的监视器|可能使一些清理性的工作得不到完成，释放锁之后造成的数据同步问题，难以检测   
  - resume()：This method exists solely for use with suspend()，which has been depreated it is deadlock-prone, 只和suspend()一起出现，suspend()暂停线程，resume()恢复线程执行  
  - suspend()：It is inherently deadlock-prone 本质上容易死锁，用来暂停线程  
  - interrupt()：Interrupts this thread 并不能类似于break语句一样，马上就可以停止线程，调用该方法只是打了一个标记，需要判断条件才能中断  
  - isInterrupt()：Test whether this thread has been interrupted  判断线程是否被打上中断标记
  - interrupted()：Test whether the current thread has been interrupted  判断当前线程是否被打上中断标记  
 5.1 使用stop()，释放了锁导致结果错误
 ```java
 class Chapter01_B5_02 {
	private String username = "a";
	private String password = "aa";
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername() {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		this.password = password;
	}
	
	public void printString(String username, String password) {
		try{
			this.username = username;
			Thread.sleep(100000);
			this.password = password;
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}

class Chapter01_B5_03 extends Thread{
	private Chapter01_B5_02 chapter01_b5_02;
	
	public Chapter01_B5_03(Chapter01_B5_02 chapter01_b5_02){
		super();
		this.chapter01_b5_02 = chapter01_b5_02;
		System.out.println(this.chapter01_b5_02.getUsername());
		System.out.println(this.chapter01_b5_02.getPassword());
	}
	
	@Override
	public void run() {
		chapter01_b5_02.printString("b", "bb");
	}
	
	public static void main(String[] args){
		try{
			Chapter01_B5_02 chapter01_b5_02 = new Chapter01_B5_02();
			Chapter01_B5_03 chapter01_b5_03 = new Chapter01_B5_03(chapter01_b5_02);
			
			chapter01_b5_03.start();
			
			/**
			 * result:
			 * a
			 * aa
			 * b aa
			 *
			 * explain:在初始化对象的时候，调用构造方法，username = a, password = aa
			 * 然后另一个线程执行run()时，覆盖了username = b, 线程休息100s，在线程休息的时候stop了线程
			 * 因此username = b, password = aa(使用的是原值，未覆盖)
			 *
			 * 如果run()的休眠时间为10ms的时候，那么最终的结果是b bb
			 */
			Thread.sleep(1000);
			chapter01_b5_03.stop();
			System.out.println(chapter01_b5_02.getUsername() + " " + chapter01_b5_02.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
 ```
5.2 interrputed()和isInterrupted()的用法，以及是否清除状态？
```java
public class Chapter01_B0 extends Thread {
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 50000; i ++ ) {
			System.out.println("i = " + i);
		}
	}


	/**
	 * interrupt(): interrupts this thread  void
	 * interrupted(): Tests whether the current thread has been interrupted  static boolean
	 * isInterrupted(): Tests whether this thread has been interrupted   boolean
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter01_B0 chapter01_b0 = new Chapter01_B0();
			chapter01_b0.start();
			Thread.sleep(1000);

			/**
			 * 但是在这个例子中，即使调用了interrupt()，但是也未能及时的终止线程
			 */
			chapter01_b0.interrupt();
			// result:
			/**
			 * 从结果来看线程并未停止，interrupted(): 测试当前线程是否已经中断，这个当前线程就是main,从未中断过
			 * Thread interrupted A ?:false
			 * Thread interrupted B ?:false
			 */
			System.out.println("Thread interrupted A ?:" + chapter01_b0.getName() + " " + chapter01_b0.interrupted());
			System.out.println("Thread interrupted B ?:" + chapter01_b0.interrupted());

			Thread.currentThread().interrupt();

			/**
			 * result:
			 * main 疑问: 竟然终止了main主线程，为什么还能继续执行下去?
			 * ThreadA interrupted ?:true
			 * ThreadB interrupted ?:false
			 */
			System.out.println(Thread.currentThread().getName());

			/**
			 * interrupted():
			 * Tests whether the current thread has been interrupted. The interrupted status of the thread is cleared by this method.
			 * In other words, if this method were to be called twice in succession, the second call would return false(unless the thread
			 * current were interrupted again, after the first cleared its interrupted status and before the second call had examined it)
			 *
			 * 测试当前线程是否中断，线程中断状态由该方法清除，如果连续两次调用该方法，则第二次调用返回false(在第一次调用清除了其中断状态之后，且第二次调用检验完中断状态之前)
			 */
			System.out.println("ThreadA interrupted ?:" + Thread.interrupted());
			System.out.println("ThreadB interrupted ?:" + Thread.interrupted());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}

```
> 总结：  
> this.interrupted()：测试当前线程是否已经中断，执行后将具有将状态标志置清除为false的功能  
> this.isInterrupted()：测试线程线程是否中断，不清楚状态  
5.3 停止的线程？真的停止了吗？
```java
class Chapter01_B3_01 extends Thread{

	/**
	 * 通过抛异常来 终止线程(原因是在这里使用break和return，for循环底下的is this thread interrupted? 还是会打印出来，因此需要用抛异常的方法)
	 */
	@Override
	public void run() {
		try{
			for(int i = 0; i < 500000; i ++) {
				System.out.println("i = " + i);
				if(this.isInterrupted()) {
					System.out.println("interrupt this thread");
					throw new InterruptedException();
					// return ;
					// break;
				}
			}
			System.out.println("is this thread interrupted?");
		}catch (Exception e) {
			System.out.println("this thread get into exception");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try{
			Chapter01_B3_01 chapter01_b3_01 = new Chapter01_B3_01();
			chapter01_b3_01.start();
			Thread.sleep(1000);
			chapter01_b3_01.interrupt();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
```
5.4 如果线程处于sleep()状态下，调用interrupt()会发生什么？
> 在sleep()停止线程，会进入sleep的catch语句，并且清除停止状态值，使之变成false
5.5 使用interrupt()和isInterrupted()和return停止线程？
```java
public class Chapter01_B6 extends Thread{
	
	@Override
	public void run() {
	
		while(true) {
			if(this.isInterrupted()) {
				System.out.println("this thread has interrupted!");
				return ;
			}
			System.out.println("a");
		}
	
	}
	
	public static void main(String[] args) {
		try{
			/**
			 * 建议使用"抛异常"的方法来实现线程的停止，因为在catch块中还可以将异常向上抛，
			 * 使线程停止的事件得以传播
			 */
			Chapter01_B6 chapter01_b6 = new Chapter01_B6();
			chapter01_b6.start();
			System.out.println(Thread.currentThread().getName() + " ");
			Thread.sleep(1);
			chapter01_b6.interrupt();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
```
6. 线程的优先级？守护线程？
- 线程的优先级为1~10，默认为5，优先级越高的线程得到的CPU资源就较多，也即优先级越高的任务越容易首先执行完；具有继承性
- 守护线程：当进程中没有非守护线程，则守护线程就自动销毁，daemon线程的作用是为其他线程的运行提供便利服务，典型应用是GC

