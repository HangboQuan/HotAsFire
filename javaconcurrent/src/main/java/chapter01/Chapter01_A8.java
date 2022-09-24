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

class Chapter01_A8_02 extends Thread {

	/**
	 * 构造函数是被main()调用的，这里的Thread.currentThread()返回的是主线程main
	 * this指向的是当前线程 chapter01_a8_02, this.getName() = Thread-0由内部机制决定,线程new的时候就自动生成一个默认的线程名
	 */
	public Chapter01_A8_02() {
		System.out.println("---------" + "构造函数开始" + "-------------");
		System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().isAlive());
		System.out.println("this.getName() = " + this.getName());
		System.out.println("this.isAlive() = " + this.isAlive());
		System.out.println("Thread.currentThread() == this:" + (Thread.currentThread() == this));
		System.out.println("---------" + "构造函数结束" + "-------------");
	}


	/**
	 * 线程开始启动,Thread.currentThread()和this指同一个,即当前线程
	 * Thread.currentThread()表示当前代码被谁调用，this指的是真正的当前线程
	 */
	@Override
	public void run() {
		System.out.println("----------" + "run()开始" + "---------------");
		System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
		System.out.println("Thread.currentThread().isAlive() = " + Thread.currentThread().isAlive());
		System.out.println("this.getName() = " + this.getName());
		System.out.println("this.isAlive() = " + this.isAlive());
		System.out.println("Thread.currentThread() = this:" + (Thread.currentThread() == this));
		System.out.println("----------" + "run()结束" + "-----------------");
	}


	/**
	 * ---------构造函数开始-------------
	 * Thread.currentThread().getName() = main
	 * Thread.currentThread().getName() = true
	 * this.getName() = Thread-0
	 * this.isAlive() = false
	 * Thread.currentThread() == this:false
	 * ---------构造函数结束-------------
	 * ----------run()开始---------------
	 * Thread.currentThread().getName() = ThreadA
	 * Thread.currentThread().isAlive() = true
	 * this.getName() = ThreadA
	 * this.isAlive() = true
	 * Thread.currentThread() = this:true
	 * ----------run()结束-----------------
	 * @param args
	 */
	public static void main(String[] args){
		/*Chapter01_A8_02 chapter01_a8_02 = new Chapter01_A8_02();
		chapter01_a8_02.setName("ThreadA");
		chapter01_a8_02.start();*/


		/**
		 * ---------构造函数开始-------------
		 * Thread.currentThread().getName() = main
		 * Thread.currentThread().getName() = true
		 * this.getName() = Thread-0
		 * this.isAlive() = false
		 * Thread.currentThread() == this:false
		 * ---------构造函数结束-------------
		 * ----------run()开始---------------
		 * Thread.currentThread().getName() = ThreadA
		 * Thread.currentThread().isAlive() = true
		 * this.getName() = Thread-0
		 * this.isAlive() = false
		 * Thread.currentThread() = this:false
		 * ----------run()结束-----------------
		 */

		/**
		 * 线程chapter01_a8_02作为一个参数传给另一个参数
		 * 内部线程chapter01_a8_02并没有开启
		 * 两者指向了不同的线程对象，Thread.currentThread()指向的是外部线程thread, this指向的是内部线程chapter01_a8_02
		 *
		 * 总结: 当以参数传递的形式把线程传递给另一个线程，Thread.currentThreads()指向当前方法被哪个方法调用的对象，外部线程
		 * this指向的是真正去执行run()的对象
		 */
		Chapter01_A8_02 chapter01_a8_02 = new Chapter01_A8_02();
		Thread thread = new Thread(chapter01_a8_02);
		thread.setName("ThreadA");
		thread.start();

	}
}

