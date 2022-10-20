package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-20 下午8:50
 */
public class Chapter02_C3 {

	public static void printA() {
		synchronized (Chapter02_C3.class) {

			try {
				System.out.println("printA begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(3000);
				System.out.println("printA end ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public static void printB() {
		synchronized (Chapter02_C3.class) {
			try {
				System.out.println("printB begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("printB end ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter02_C3_01 extends Thread {


	private Chapter02_C3 chapter02_c3;

	public Chapter02_C3_01(Chapter02_C3 chapter02_c3) {
		this.chapter02_c3 = chapter02_c3;
	}

	@Override
	public void run() {
		chapter02_c3.printA();
	}
}

class Chapter02_C3_02 extends Thread {


	private Chapter02_C3 chapter02_c3;

	public Chapter02_C3_02(Chapter02_C3 chapter02_c3) {
		this.chapter02_c3 = chapter02_c3;
	}

	@Override
	public void run() {
		chapter02_c3.printB();
	}
}

class Chapter02_C3_03 {

	public static void main(String[] args) {
		/**
		 * 只声明一个对象时的结果, 以及下面声明两个对象时的结果是相同的, 而且不论同步方法是否为静态方法
		 * result:
		 * printA begin ThreadName = ThreadA 1666270880472
		 * printA end ThreadName = ThreadA 1666270883472
		 * printB begin ThreadName = ThreadB 1666270883473
		 * printB end ThreadName = ThreadB 1666270885473
		 */
		Chapter02_C3 chapter02_c3_A = new Chapter02_C3();
		Chapter02_C3 chapter02_c3_B = new Chapter02_C3();

		Chapter02_C3_01 chapter02_c3_01 = new Chapter02_C3_01(chapter02_c3_A);
		chapter02_c3_01.setName("ThreadA");
		chapter02_c3_01.start();

		Chapter02_C3_02 chapter02_c3_02 = new Chapter02_C3_02(chapter02_c3_B);
		chapter02_c3_02.setName("ThreadB");
		chapter02_c3_02.start();


	}
}