package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/16 10:58
 */
public class Chapter01_A6 extends Thread{
	
	private int i = 5;
	/**
	 * 可以看到System.out.println()也可以说是同步方法, 但是这个运行结果仍然会出现非线程
	 * 安全问题， 原因是这i --非原子操作在进入System.out.println()的时候就执行了
	 * println(String x){
	 *     synchronized(this) {
	 *         print();
	 *         newLine();
	 *     }
	 * }
	 */
	@Override
	public void run(){
		System.out.println("i=" + (i --) + " threadName=" + Thread.currentThread().getName());
	}
}

class Chapter01_A6_01{
	public static void main(String[] args){
		
		/**
		 * result:
		 * i=4 threadName=Thread-4
		 * i=5 threadName=Thread-1
		 * i=3 threadName=Thread-3
		 * i=5 threadName=Thread-5
		 * i=2 threadName=Thread-2
		 */
		Chapter01_A6 chapter01_a6 = new Chapter01_A6();
		Thread t1 = new Thread(chapter01_a6);
		Thread t2 = new Thread(chapter01_a6);
		Thread t3 = new Thread(chapter01_a6);
		Thread t4 = new Thread(chapter01_a6);
		Thread t5 = new Thread(chapter01_a6);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}
