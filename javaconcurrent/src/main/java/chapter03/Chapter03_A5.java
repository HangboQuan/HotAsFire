package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 上午9:49
 */
public class Chapter03_A5 {

	public void serviceMethodA(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin wait() ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				lock.wait();
				System.out.println("end wait() ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void serviceMethodB(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin notify() ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				lock.notify();
				Thread.sleep(5000);
				System.out.println("end notify() ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_A5_01 extends Thread {

	private Object lock;

	public Chapter03_A5_01(Object lock) {
		this.lock = lock;
	}


	@Override
	public void run() {
		Chapter03_A5 chapter03_a5 = new Chapter03_A5();
		chapter03_a5.serviceMethodA(lock);
	}
}

class Chapter03_A5_02 extends Thread {

	private Object lock;

	public Chapter03_A5_02(Object lock) {
		this.lock = lock;
	}


	@Override
	public void run() {
		Chapter03_A5 chapter03_a5 = new Chapter03_A5();
		chapter03_a5.serviceMethodB(lock);
	}
}

class Chapter03_A5_03 extends Thread {

	private Object lock;

	public Chapter03_A5_03(Object lock) {
		this.lock = lock;
	}


	@Override
	public void run() {
		Chapter03_A5 chapter03_a5 = new Chapter03_A5();
		chapter03_a5.serviceMethodB(lock);
	}
}


class Chapter03_A5_04 {


	/**
	 * 该例子再次说明notify()并不会立即释放锁, 而是要等同步块的代码执行完之后, 才会释放锁, 唤醒被wait()的线程,
	 * 被wait()的线程处于就绪状态，重新去竞争锁，等CPU有时间调度的话，就处于运行状态
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_A5 chapter03_a5 = new Chapter03_A5();
		Chapter03_A5_01 chapter03_a5_01 = new Chapter03_A5_01(chapter03_a5);
		chapter03_a5_01.setName("ThreadA");
		chapter03_a5_01.start();

		Chapter03_A5_02 chapter03_a5_02 = new Chapter03_A5_02(chapter03_a5);
		chapter03_a5_02.setName("ThreadB");
		chapter03_a5_02.start();

		Chapter03_A5_03 chapter03_a5_03 = new Chapter03_A5_03(chapter03_a5);
		chapter03_a5_03.setName("ThreadC");
		chapter03_a5_03.start();
	}
}