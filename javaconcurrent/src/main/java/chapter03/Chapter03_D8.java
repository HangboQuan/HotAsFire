package chapter03;

/**
 * @author quanhangbo
 * @date 22-12-6 上午9:31
 */
public class Chapter03_D8 extends Thread {

	/**
	 * 开启3个线程，A线程打印A,B线程打印B,C线程打印C
	 * 打印顺序为A B C A B C A B C
	 * 每个线程打印10次
	 */

	private volatile int flag = 0;
	private static volatile int count = 0;
	private static final int MAX_VALUE = 30;
	private String printValue;

	public Chapter03_D8(String printValue, int flag) {
		this.printValue = printValue;
		this.flag = flag;
	}


	@Override
	public void run() {
		synchronized (this) {
			while (count < MAX_VALUE) {
				if (count % 3 == flag) {
					System.out.println(Thread.currentThread().getName() + " " + this.printValue);
					count ++;
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	public static void main(String[] args) {
		new Chapter03_D8("A", 0).start();
		new Chapter03_D8("B", 1).start();
		new Chapter03_D8("C", 2).start();
	}
}

class Chapter03_D8_01 extends Thread {

	// 开启4个线程 A线程打印1~25 B线程打印26~50 C线程打印51~75 D线程打印76~100
	// 最后累计到10000, 也就是每个打印10次

	private int count = 0;
	private int flag;
	private static final int MAX_VALUE = 100;
	private Object object = new Object();


	public Chapter03_D8_01(int flag) {
		this.flag = flag;
	}
	@Override
	public void run() {
		synchronized (object) {
			while (count < MAX_VALUE) {
				if ((count / 25) % 4 == flag) {
					System.out.println(Thread.currentThread().getName() + " " + (count + 1));

				}
				count ++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new Chapter03_D8_01(0).start();
		new Chapter03_D8_01(1).start();
		new Chapter03_D8_01(2).start();
		new Chapter03_D8_01(3).start();
	}
}
