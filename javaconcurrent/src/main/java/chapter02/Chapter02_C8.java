package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-23 上午11:03
 */
public class Chapter02_C8 implements Runnable {

	private String username;
	private Object object1 = new Object();
	private Object object2 = new Object();


	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 不同的线程都在等待不可能释放的锁，从而导致所有的任务都无法继续完成
	 */
	@Override
	public void run() {
		if(username.equals("a")) {
			synchronized (object1) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (object2) {
					System.out.println("按object1 -> object2 代码顺序来执行");
				}
			}

		}

		if(username.equals("b")) {
			synchronized (object2) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (Exception e) {
					e.printStackTrace();
				}

				synchronized (object1) {
					System.out.println("按object2 -> object1 代码顺序执行");
				}
			}


		}
	}
}

class Chapter02_C8_01 extends Thread {

	public static void main(String[] args) {
		try {
			Chapter02_C8 chapter02_c8 = new Chapter02_C8();

			chapter02_c8.setUsername("a");
			Thread thread1 = new Thread(chapter02_c8);
			thread1.start();
			Thread.sleep(10);

			chapter02_c8.setUsername("b");
			Thread thread2 = new Thread(chapter02_c8);
			thread2.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
