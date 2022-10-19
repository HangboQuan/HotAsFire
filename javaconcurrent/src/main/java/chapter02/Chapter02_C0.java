package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-19 上午9:48
 */
public class Chapter02_C0 {

}

class Chapter02_C0_01 {

	public void serviceMethod(Chapter02_C0 chapter02_c0) {
		/**
		 * synchronized(非this对象) 将x对象作为对象监视器
		 * 1.多个线程同时执行synchronized(x), 同步代码块时呈同步效果
		 * 2.其他线程执行x对象中synchronized同步方法呈同步效果
		 * 3.其他线程执行x对象中的synchronized(this)代码块也是同步效果
		 */
		synchronized (chapter02_c0) {

			try {
				System.out.println("serviceMethod getLock time = " + System.currentTimeMillis() + " ThreadName = " + Thread.currentThread().getName());
				Thread.sleep(2000);
				System.out.println("serviceMethod releaseLoak time = " + System.currentTimeMillis() + " ThreadName = " + Thread.currentThread().getName());
			} catch (Exception e) {
				e.printStackTrace();
			}


		}
	}
}

class Chapter02_C0_02 extends Thread {
	private Chapter02_C0 chapter02_c0;
	private Chapter02_C0_01 chapter02_c0_01;

	public Chapter02_C0_02(Chapter02_C0 chapter02_c0, Chapter02_C0_01 chapter02_c0_01) {
		this.chapter02_c0 = chapter02_c0;
		this.chapter02_c0_01 = chapter02_c0_01;
	}

	@Override
	public void run() {
		chapter02_c0_01.serviceMethod(chapter02_c0);
	}
}

class Chapter02_C0_03 extends Thread {
	private Chapter02_C0 chapter02_c0;
	private Chapter02_C0_01 chapter02_c0_01;

	public Chapter02_C0_03(Chapter02_C0 chapter02_c0, Chapter02_C0_01 chapter02_c0_01) {
		this.chapter02_c0 = chapter02_c0;
		this.chapter02_c0_01 = chapter02_c0_01;
	}

	@Override
	public void run() {
		chapter02_c0_01.serviceMethod(chapter02_c0);
	}
}

class Chapter02_C0_04 {

	/**
	 * result:
	 * serviceMethod getLock time = 1666145037573 ThreadName = ThreadA
	 * serviceMethod releaseLoak time = 1666145039574 ThreadName = ThreadA
	 * serviceMethod getLock time = 1666145039574 ThreadName = ThreadB
	 * serviceMethod releaseLoak time = 1666145041574 ThreadName = ThreadB
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_C0 chapter02_c0 = new Chapter02_C0();
		Chapter02_C0_01 chapter02_c0_01 = new Chapter02_C0_01();
		Chapter02_C0_02 chapter02_c0_02 = new Chapter02_C0_02(chapter02_c0, chapter02_c0_01);
		chapter02_c0_02.setName("ThreadA");
		chapter02_c0_02.start();

		Chapter02_C0_03 chapter02_c0_03 = new Chapter02_C0_03(chapter02_c0, chapter02_c0_01);
		chapter02_c0_03.setName("ThreadB");
		chapter02_c0_03.start();
	}
}

class Chapter02_C0_05 {

	/**
	 * 等价于synchronized(this)
	 */

	synchronized public void serviceMethodA() {
		try {
			System.out.println("serviceMethodA getLock threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(3000);
 			System.out.println("serviceMethodA releaseLock threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter02_C0_06 {

	public void serviceMethodB(Chapter02_C0_05 chapter02_c0_05) {
		synchronized (chapter02_c0_05) {
			try {
				System.out.println("serviceMethodB getLock threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("serviceMethodB releaseLock threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}

class Chapter02_C0_07 extends Thread {
	private Chapter02_C0_05 chapter02_c0_05;

	public Chapter02_C0_07(Chapter02_C0_05 chapter02_c0_05, Chapter02_C0_06 chapter02_c0_06) {
		this.chapter02_c0_05 = chapter02_c0_05;
	}

	@Override
	public void run() {
		chapter02_c0_05.serviceMethodA();
	}
}

class Chapter02_C0_08 extends Thread {
	private Chapter02_C0_05 chapter02_c0_05;

	private Chapter02_C0_06 chapter02_c0_06;

	public Chapter02_C0_08(Chapter02_C0_05 chapter02_c0_05, Chapter02_C0_06 chapter02_c0_06) {
		this.chapter02_c0_05 = chapter02_c0_05;
		this.chapter02_c0_06 = chapter02_c0_06;
	}

	@Override
	public void run() {
		chapter02_c0_06.serviceMethodB(chapter02_c0_05);
	}
}

class Chapter02_C0_09 {

	public static void main(String[] args) {

		/**
		 * 这里使用synchronized锁方法，和synchronized(对象)该对象和this是不等价的，
		 * synchronized(对象)锁的是对象chapter02_c0_05
		 * synchronized(this)锁的是哪个实例去调它，锁的是对象chapter02_c0_06
		 *
		 * result:
		 * serviceMethodA getLock threadName = ThreadA 1666182389469
		 * serviceMethodA releaseLock threadName = ThreadA 1666182392469
		 * serviceMethodB getLock threadName = ThreadB 1666182392469
		 * serviceMethodB releaseLock threadName = ThreadB 1666182394470
		 */
		Chapter02_C0_05 chapter02_c0_05 = new Chapter02_C0_05();
		Chapter02_C0_06 chapter02_c0_06 = new Chapter02_C0_06();

		Chapter02_C0_07 chapter02_c0_07 = new Chapter02_C0_07(chapter02_c0_05, chapter02_c0_06);
		chapter02_c0_07.setName("ThreadA");
		chapter02_c0_07.start();

		Chapter02_C0_08 chapter02_c0_08 = new Chapter02_C0_08(chapter02_c0_05, chapter02_c0_06);
		chapter02_c0_08.setName("ThreadB");
		chapter02_c0_08.start();
	}
}



