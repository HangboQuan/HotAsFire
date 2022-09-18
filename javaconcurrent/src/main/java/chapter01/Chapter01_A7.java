package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/18 19:09
 */
public class Chapter01_A7 {
	
	public static void main(String[] args){
		// currentThread()方法可以返回代码段正在哪个线程调用的信息
		// result: main -> main()被名为main线程调用
		System.out.println(Thread.currentThread().getName());
	}
}

class Chapter01_A7_01 extends Thread{
	
	public Chapter01_A7_01() {
		System.out.println("构造方法是被" + Thread.currentThread().getName() + "调用");
	}
	
	@Override
	public void run() {
		System.out.println("run方法是被" + Thread.currentThread().getName() + "调用");
	}

	/**
	 * 构造方法是被main调用
	 * run方法是被ThreadA调用
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter01_A7_01 chapter01_a7_01 = new Chapter01_A7_01();
		chapter01_a7_01.setName("ThreadA");
		chapter01_a7_01.start();
	}
}

class Chapter01_A7_02 extends Thread {
	public Chapter01_A7_02() {
		System.out.println("Chapter01_A7_02()---begin");
		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		// this指的是当前的对象，所以也就是当前对象的线程名
		System.out.println("this.getName()=" + this.getName());
		try{
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Chapter01_A7_02()---end");
	}
	
	@Override
	public void run() {
		System.out.println("run---begin");
		System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());
		System.out.println("this.getName()=" + this.getName());
		System.out.println("run---end");
	}

	/**
	 * result: 构造方法总是在run方法调用之前执行完，这是必然事件吗？
	 * 为什么没有出现构造方法和run()交替打印的结果？
	 * Chapter01_A7_02()---begin
	 * Thread.currentThread().getName()=main
	 * this.getName()=Thread-0
	 * Chapter01_A7_02()---end
	 * run---begin
	 * Thread.currentThread().getName()=A
	 * this.getName()=A
	 * run---end
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter01_A7_02 chapter01_a7_02 = new Chapter01_A7_02();
		chapter01_a7_02.setName("A");
		chapter01_a7_02.start();
	}
	
	
}