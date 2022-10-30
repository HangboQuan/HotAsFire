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

6. 如何使线程暂停？ 
  - stop()：
  - resume()：
  - suspend()：
  - interrupt()：
  - isInterrupt()：
  - interrupted()：
8. 线程的优先级？
9. 线程安全问题？
