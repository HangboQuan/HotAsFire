package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/18 19:38
 */
public class Chapter01_A8 extends Thread{
	
	// isAlive()的功能是判断当前线程是否处于活动状态
	// 活动状态是线程已经启动且尚未终止，线程处于正在运行或准备开始的状态，就认为线程是存活的
	@Override
	public void run() {
		System.out.println("run=" + this.isAlive());
	}


	/**
	 * result:
	 * begin == false
	 * end == true
	 * run=true
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter01_A8 chapter01_a8 = new Chapter01_A8();
		System.out.println("begin == " + chapter01_a8.isAlive());
		chapter01_a8.start();
		try{
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * 加上休眠时间的时间的话，线程已经运行结束了
		 * result:
		 * begin == false
		 * run=true
		 * end == false
		 */
		System.out.println("end == " + chapter01_a8.isAlive());
	}
}

class Chapter01_A8_01 extends Thread{
	
	public Chapter01_A8_01() {
		System.out.println("Chapter01_A8_01---begin");
		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("Thread.currentThread() == this:" + (Thread.currentThread() == this));
		System.out.println("Chapter01_A8_01---end");
	}
	
	
	@Override
	public void run() {
		System.out.println("run---begin");
		System.out.println("Thread.currentThreadName().getName()=" + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("this.isAlive()=" + this.isAlive());
		System.out.println("Thread.currentThread() == this:" + (Thread.currentThread() == this));
		System.out.println("run---end");
	}


	
	public static void main(String[] args){
		/**
		 * result:
		 * Chapter01_A8_01---begin
		 * Thread.currentThreadName().getName()=main
		 * Thread.currentThread().isAlive()=true
		 * this.getName()=Thread-0
		 * this.isAlive()=false
		 * Chapter01_A8_01---end
		 * main begin chapter01_a8_01 isAlive=false
		 * main end chapter01_a8_01 isAlive=true
		 * run---begin
		 * Thread.currentThreadName().getName()=ThreadA
		 * Thread.currentThread().isAlive()=true
		 * this.getName()=ThreadA
		 * this.isAlive()=true
		 * run---end
		 *
		 */
		/*Chapter01_A8_01 chapter01_a8_01 = new Chapter01_A8_01();
		System.out.println("main begin chapter01_a8_01 isAlive=" + chapter01_a8_01.isAlive());
		chapter01_a8_01.setName("ThreadA");
		chapter01_a8_01.start();
		System.out.println("main end chapter01_a8_01 isAlive=" + chapter01_a8_01.isAlive());*/
		
		
		/**
		 * result: 如果将线程对象以构造函数的方式传给Thread对象进行start()启动时, run()中的this.isAlive()是有区别的
		 * Chapter01_A8_01---begin
		 * Thread.currentThreadName().getName()=main
		 * Thread.currentThread().isAlive()=true
		 * this.getName()=Thread-0
		 * this.isAlive()=false
		 * Chapter01_A8_01---end
		 * main begin thread isAlive=false
		 * main end thread isAlive=true
		 * run---begin
		 * Thread.currentThreadName().getName()=ThreadB
		 * Thread.currentThread().isAlive()=true
		 * this.getName()=Thread-0
		 * this.isAlive()=false
		 * run---end
		 */
		
		/**
		 * 这里有个问题是：为什么this.isAlive()=false?
		 */
		Chapter01_A8_01 chapter01_a8_01 = new Chapter01_A8_01();
		Thread thread = new Thread(chapter01_a8_01);
		System.out.println("main begin thread isAlive=" + thread.isAlive());
		thread.setName("ThreadB");
		thread.start();
		System.out.println("main end thread isAlive=" + thread.isAlive());
		
	}
}
