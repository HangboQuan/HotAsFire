package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/15 20:03
 */

/**
 * 线程不共享
 */
public class Chapter01_A4 extends Thread{
	
	private int count = 5;
	
	public Chapter01_A4(String name){
		super();
		// 设置线程名称
		this.setName(name);
	}

	@Override
	public void run(){
		super.run();
		while(count > 0){
			count --;
			System.out.println("由 " + Thread.currentThread().getName() + "计算，count=" + count);
		}
	}
	
	public static void main(String[] args){
		
		/**
		 * result:
		 * 由 ThreadB计算，count=4
		 * 由 ThreadB计算，count=3
		 * 由 ThreadB计算，count=2
		 * 由 ThreadB计算，count=1
		 * 由 ThreadB计算，count=0
		 * 由 ThreadA计算，count=4
		 * 由 ThreadC计算，count=4
		 * 由 ThreadC计算，count=3
		 * 由 ThreadC计算，count=2
		 * 由 ThreadC计算，count=1
		 * 由 ThreadC计算，count=0
		 * 由 ThreadA计算，count=3
		 * 由 ThreadA计算，count=2
		 * 由 ThreadA计算，count=1
		 * 由 ThreadA计算，count=0
		 */
		Chapter01_A4 chapter01_a4 = new Chapter01_A4("ThreadA");
		Chapter01_A4 chapter01_a4_01 = new Chapter01_A4("ThreadB");
		Chapter01_A4 chapter01_a4_02 = new Chapter01_A4("ThreadC");
		
		/**
		 * 一共创建了3个线程，每个线程都有对应的count值，自己减少自己的count变量的值
		 * 这种情况是变量不共享，不存在多个线程访问同一个实例变量的情况
		 */
		chapter01_a4.start();
		chapter01_a4_01.start();
		chapter01_a4_02.start();
	}
}

/**
 * 线程共享
 */
class Chapter01_A4_01 extends Thread{
	private int count = 10;
	
	@Override
	public void run(){
		while(count > 0){
			count --;
			System.out.println("由 " + Thread.currentThread().getName() + "计算，count=" + count);
		}
		
	}

	/**
	 * 线程A和线程B打印出的count的值都是7，即A，B, C三个线程同时对count进行操作，产生了
	 * 非线程安全问题
	 *
	 * 另： count --操作的非原子性
	 * 1. 取得原有的count值
	 * 2. 计算count - 1
	 * 3. count = count - 1赋值(主要出现在赋值的问题上，如果多个线程同时访问，那么一定会出现非线程安全问题)
	 * @param args
	 */
	public static void main(String[] args){
		/**
		 * result:
		 * 由 ThreadB计算，count=7
		 * 由 ThreadC计算，count=7
		 * 由 ThreadA计算，count=7
		 * 由 ThreadC计算，count=5
		 * 由 ThreadB计算，count=6
		 * 由 ThreadC计算，count=3
		 * 由 ThreadA计算，count=4
		 * 由 ThreadC计算，count=1
		 * 由 ThreadB计算，count=2
		 * 由 ThreadA计算，count=0
		 */
		Chapter01_A4_01 chapter01_a4_01 = new Chapter01_A4_01();
		
		Thread a = new Thread(chapter01_a4_01, "ThreadA");
		Thread b = new Thread(chapter01_a4_01, "ThreadB");
		Thread c = new Thread(chapter01_a4_01, "ThreadC");
		
		a.start();
		b.start();
		c.start();
	}
}
