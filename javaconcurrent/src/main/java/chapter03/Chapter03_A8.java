package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午9:30
 */
public class Chapter03_A8 {


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