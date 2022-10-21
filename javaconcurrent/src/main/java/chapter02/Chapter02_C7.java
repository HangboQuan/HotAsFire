package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-21 下午8:12
 */
public class Chapter02_C7 {

	synchronized public void methodA() {
		try {
			System.out.println("methodA begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(2000);
			methodB();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	synchronized public void methodB() {
		try {
			System.out.println("methodB begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(5000);
			methodA();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_C7_01 extends Thread {

	private Chapter02_C7 chapter02_c7;

	public Chapter02_C7_01(Chapter02_C7 chapter02_c7) {
		this.chapter02_c7 = chapter02_c7;
	}


	@Override
	public void run() {
		chapter02_c7.methodA();
	}
}

class Chapter02_C7_02 extends Thread {
	private Chapter02_C7 chapter02_c7;

	public Chapter02_C7_02(Chapter02_C7 chapter02_c7) {
		this.chapter02_c7 = chapter02_c7;
	}


	@Override
	public void run() {
		chapter02_c7.methodB();
	}
}

class Chapter02_C7_03 {

	public static void main(String[] args) {

		Chapter02_C7 chapter02_cA = new Chapter02_C7();
		Chapter02_C7 chapter02_cB = new Chapter02_C7();

		Chapter02_C7_01 chapter02_c7_01 = new Chapter02_C7_01(chapter02_cA);
		chapter02_c7_01.setName("ThreadA");
		chapter02_c7_01.start();

		Chapter02_C7_02 chapter02_c7_02 = new Chapter02_C7_02(chapter02_cB);
		chapter02_c7_02.setName("ThreadB");
		chapter02_c7_02.start();
	}
}