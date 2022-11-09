package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-9 上午9:03
 */
public class Chapter03_A9 {

	private String lock  = new String("");

	private Runnable runnableA = new Runnable() {
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("begin wait");
					lock.wait();
					System.out.println("end wait");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private Runnable runnableB = new Runnable() {
		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("begin notify");
					lock.notify();
					System.out.println("end notify");
				}
			} catch (Exception e) {

			}
		}
	};


	/**
	 * 需要注意的一点是 必须先wait()然后才能在notify(), 如果顺序不对，同样也会造成线程阻塞
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Chapter03_A9 chapter03_a9 = new Chapter03_A9();
			Thread threadB = new Thread(chapter03_a9.runnableB);
			threadB.start();

			Thread threadA = new Thread(chapter03_a9.runnableA);
			threadA.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_A9_01 {
	private String lock = new String("");
	private boolean isFirstRun = false;

	private Runnable runnableA = new Runnable() {
		@Override
		public void run() {
			try {
				synchronized (lock) {
					while(isFirstRun == false) {
						System.out.println("begin wait");
						lock.wait();
						System.out.println("end wait");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};

	private Runnable runnableB = new Runnable() {
		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("notify begin");
				lock.notify();
				System.out.println("notify end");
				isFirstRun = true;
			}
		}
	};

	public static void main(String[] args) {

		try {
			Chapter03_A9_01 chapter03_a9_01 = new Chapter03_A9_01();

			Thread threadA = new Thread(chapter03_a9_01.runnableA);
			threadA.start();
			Thread.sleep(200);

			Thread threadB = new Thread(chapter03_a9_01.runnableB);
			threadB.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}