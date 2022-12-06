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
	// 最后累计到100, 也就是每个打印1次

	// 问题1 这里count和flag 如果全部加volatile可以实现该功能，如果不加volatile也可以实现，是事实还是巧合 （是事实）
	// 问题2 第一个线程进入while之后，由于while一直无法结束，其他线程是怎么抢占到锁，进而进入方法，实现打印? （是能够结束的，每个线程都有一个count属性，也都是从0开始的累加的）

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
			// 因此每个线程都会一个count值, 是和其他线程隔离的，原因是不同的对象也不可能出现共享，自然也就不可能有线程安全问题
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
		// 这里并没有共享对象,每创建一个对象就有一个对应count,flag
		new Chapter03_D8_01(0).start();
		new Chapter03_D8_01(1).start();
		new Chapter03_D8_01(2).start();
		new Chapter03_D8_01(3).start();
	}
}
