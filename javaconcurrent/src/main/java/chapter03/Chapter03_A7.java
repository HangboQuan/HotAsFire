package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午9:10
 */
public class Chapter03_A7 {

	public void serviceMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin wait() ThreadName = " + Thread.currentThread().getName());
				lock.wait();
				System.out.println("end wait() ThreadName = " + Thread.currentThread().getName());
			}
		} catch (Exception e) {

		}
	}
}

class Chapter03_A7_01 extends Thread {

	private Object lock;

	public Chapter03_A7_01(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_02 extends Thread {

	private Object lock;

	public Chapter03_A7_02(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_03 extends Thread {

	private Object lock;

	public Chapter03_A7_03(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_04 extends Thread {
	private Object lock;

	public Chapter03_A7_04(Object lock) {
		this.lock = lock;
	}

	/**
	 * 如果是多个notify(), 那么所有的wait()等待的方法都会被唤醒
	 *
	 * 这里可以改为notifyAll(), 如果notify()小于wait()的数量，就总会使线程处于等待的状态，从而永远无法得到唤醒
	 */
	@Override
	public void run() {
		synchronized (lock) {
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();

			lock.notifyAll();
		}
	}
}


class Chapter03_A7_05 {

	public static void main(String[] args) {

		/**
		 * 验证了notify()只能随机的唤醒一个线程
		 * result:
		 * begin wait() ThreadName = Thread-0
		 * begin wait() ThreadName = Thread-1
		 * begin wait() ThreadName = Thread-2
		 * end wait() ThreadName = Thread-0
		 */
		try {
			Object obj = new Object();

			Chapter03_A7_01 chapter03_a7_01 = new Chapter03_A7_01(obj);
			Chapter03_A7_02 chapter03_a7_02 = new Chapter03_A7_02(obj);
			Chapter03_A7_03 chapter03_a7_03 = new Chapter03_A7_03(obj);

			chapter03_a7_01.start();
			chapter03_a7_02.start();
			chapter03_a7_03.start();

			Thread.sleep(1000);

			Chapter03_A7_04 chapter03_a7_04 = new Chapter03_A7_04(obj);
			chapter03_a7_04.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}