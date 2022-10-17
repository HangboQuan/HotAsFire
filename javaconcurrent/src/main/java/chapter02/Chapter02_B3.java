package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 下午8:03
 */
public class Chapter02_B3 {

	public void serviceMethodA() {
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " serviceMethodA begin task");
		synchronized (this) {
			try {
				System.out.println("serviceMethodA synchronized = " + Thread.currentThread().getName() + " this begin");
				Thread.sleep(3000);
				System.out.println("serviceMethodA synchronized = " + Thread.currentThread().getName() + " this end");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void serviceMethodB() {
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " serviceMethodB begin task");
		synchronized (this) {
			try {
				System.out.println("serviceMethodB synchronized = " + Thread.currentThread().getName() + " this begin");
				Thread.sleep(5000);
				System.out.println("serviceMethodB synchronized = " + Thread.currentThread().getName() + " this end");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter02_B3_01 extends Thread {

	private Chapter02_B3 chapter02_b3;

	public Chapter02_B3_01(Chapter02_B3 chapter02_b3) {
		this.chapter02_b3 = chapter02_b3;
	}

	@Override
	public void run() {
		chapter02_b3.serviceMethodA();
	}
}

class Chapter02_B3_02 extends Thread {

	private Chapter02_B3 chapter02_b3;

	public Chapter02_B3_02(Chapter02_B3 chapter02_b3) {
		this.chapter02_b3 = chapter02_b3;
	}

	@Override
	public void run() {
		chapter02_b3.serviceMethodB();
	}
}

class Chapter02_B3_03 {

	/**
	 * 可以观察到:
	 * 打印前3行　停留3s, 然后打印2行，停留5s,再打印最后一行
	 * synchronized 使用的对象监视器只有一个，如果一个object的被synchronize(this)了，则其他object的synchronized(this)是阻塞的
	 *
	 * result:
	 * ThreadName = ThreadA serviceMethodA begin task
	 * serviceMethodA synchronized = ThreadA this begin
	 * ThreadName = ThreadB serviceMethodB begin task
	 * serviceMethodA synchronized = ThreadA this end
	 * serviceMethodB synchronized = ThreadB this begin
	 * serviceMethodB synchronized = ThreadB this end
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_B3 chapter02_b3 = new Chapter02_B3();

		Chapter02_B3_01 chapter02_b3_01 = new Chapter02_B3_01(chapter02_b3);
		chapter02_b3_01.setName("ThreadA");
		chapter02_b3_01.start();

		Chapter02_B3_02 chapter02_b3_02 = new Chapter02_B3_02(chapter02_b3);
		chapter02_b3_02.setName("ThreadB");
		chapter02_b3_02.start();
	}
}