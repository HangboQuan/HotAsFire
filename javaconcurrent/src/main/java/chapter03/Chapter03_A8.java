package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午9:30
 */
public class Chapter03_A8 {


	/**
	 * 等待某一段时间是否有线程对锁进行唤醒，如果超过这个时间会自动唤醒
	 *
	 * result:
	 * wait begin = 1667954679658
	 * i will release lock
	 * can i exec 1667954681659
	 * wait end = 1667954684659
	 * @param object
	 */
	public void serviceMethodA(Object object) {
		try {
			synchronized (object) {
				System.out.println("wait begin = " + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("i will release lock");
				object.wait(3000);
				System.out.println("wait end = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void serviceMethodB(Object object) {
		try {
			synchronized (object) {
				System.out.println("can i exec " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_A8_01 extends Thread {
	private Object obj;

	public Chapter03_A8_01(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		Chapter03_A8 chapter03_a8 = new Chapter03_A8();
		chapter03_a8.serviceMethodA(obj);
	}
}

class Chapter03_A8_02 extends Thread {
	private Object obj;

	public Chapter03_A8_02(Object obj) {
		this.obj = obj;
	}

	@Override
	public void run() {
		Chapter03_A8 chapter03_a8 = new Chapter03_A8();
		chapter03_a8.serviceMethodB(obj);
	}
}

class Chapter03_A8_03 {
	public static void main(String[] args) {
		Object lock = new Object();
		Chapter03_A8_01 chapter03_a8_01 = new Chapter03_A8_01(lock);

		Chapter03_A8_02 chapter03_a8_02 = new Chapter03_A8_02(lock);

		chapter03_a8_01.start();
		chapter03_a8_02.start();
	}
}

class Chapter03_A8_04 {
	static private Object lock = new Object();
	static private Runnable runnable = new Runnable() {

		/**
		 * 过期后自动被唤醒,如果在wait(5000)在等待的时候被唤醒也同样重新获取对象锁，继续执行wait()后面的方法
		 *
		 * result:
		 * wait begin time = 1667955312973
		 * notify begin time = 1667955314973
		 * notify end time = 1667955314974
		 * wait end time = 1667955314974
		 */
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("wait begin time = " + System.currentTimeMillis());
					lock.wait(5000);

					System.out.println("wait end time = " + System.currentTimeMillis());

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	};

	static private Runnable runnable1 = new Runnable() {
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("notify begin time = " + System.currentTimeMillis());
				lock.notify();
				System.out.println("notify end time = " + System.currentTimeMillis());
			}
		}
	};
	public static void main(String[] args) {
		try {
			Thread thread = new Thread(runnable);
			thread.start();

			Thread.sleep(2000);

			Thread thread1 = new Thread(runnable1);
			thread1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}