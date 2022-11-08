package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午5:49
 */
public class Chapter03_A6 {

	public void serviceMethod(Object lock) {

		try {
			synchronized (lock) {
				System.out.println("begin exec wait() = " + System.currentTimeMillis());
				lock.wait();
				System.out.println("end exec wait() = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_A6_01 extends Thread {

	private Chapter03_A6 chapter03_a6;

	public Chapter03_A6_01(Chapter03_A6 chapter03_a6) {
		this.chapter03_a6 = chapter03_a6;
	}


	@Override
	public void run() {
		Object lock = new Object();
		chapter03_a6.serviceMethod(lock);
	}

}


class Chapter03_A6_02 {


	/**
	 * 线程呈等待状态 被线程调用会跑异常InterruptedException
	 * result:
	 * begin exec wait() = 1667901435542
	 * java.lang.InterruptedException
	 * 	at java.base/java.lang.Object.wait(Native Method)
	 * 	at java.base/java.lang.Object.wait(Object.java:326)
	 * 	at chapter03.Chapter03_A6.serviceMethod(Chapter03_A6.java:14)
	 * 	at chapter03.Chapter03_A6_01.run(Chapter03_A6.java:36)
	 *
	 * 执行同步代码块的过程中，遇到线程异常而导致线程终止，锁也会被释放
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			Chapter03_A6 chapter03_06 = new Chapter03_A6();

			Chapter03_A6_01 chapter03_a6_01 = new Chapter03_A6_01(chapter03_06);
			chapter03_a6_01.start();
			Thread.sleep(5000);
			chapter03_a6_01.interrupt();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}
