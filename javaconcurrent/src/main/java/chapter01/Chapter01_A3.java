package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/15 19:11
 */
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

