package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-6 下午7:08
 */
public class Chapter03_A2 extends Thread {

	private Object lock;

	public Chapter03_A2(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
		 	synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " begin  wait time = " + System.currentTimeMillis());
				lock.wait();
				System.out.println(Thread.currentThread().getName() + " end wait time = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter03_A2_01 extends Thread {

	private Object lock;

	public Chapter03_A2_01(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " begin  wait time = " + System.currentTimeMillis());
				lock.notify();
				System.out.println(Thread.currentThread().getName() + " end wait time = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter03_A2_02 {

	/**
	 * 从这里的耗时就可以看出: 2s后线程被notify通知唤醒
	 * result:
	 * ThreadA begin  wait time = 1667783197064
	 * ThreadB begin  wait time = 1667783199065
	 * ThreadB end wait time = 1667783199065
	 * ThreadA end wait time = 1667783199066
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Object lock = new Object();

			Chapter03_A2 chapter03_a2 = new Chapter03_A2(lock);
			chapter03_a2.setName("ThreadA");
			chapter03_a2.start();

			Thread.sleep(2000);

			Chapter03_A2_01 chapter03_a2_01 = new Chapter03_A2_01(lock);
			chapter03_a2_01.setName("ThreadB");
			chapter03_a2_01.start();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}