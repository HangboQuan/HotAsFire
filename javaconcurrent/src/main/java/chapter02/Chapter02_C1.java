package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-20 上午8:57
 */
public class Chapter02_C1 {

	synchronized public static void printA() {
		try {
			System.out.println("begin printA threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("end printA threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public static void printB() {
		try {
			System.out.println("begin printB threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("end printB threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter02_C1_01 extends Thread {

	private Chapter02_C1 chapter02_c1;

	public Chapter02_C1_01(Chapter02_C1 chapter02_c1) {
		this.chapter02_c1 = chapter02_c1;
	}


	@Override
	public void run() {
		chapter02_c1.printA();
	}
}

class Chapter02_C1_02 extends Thread {

	private Chapter02_C1 chapter02_c1;

	public Chapter02_C1_02(Chapter02_C1 chapter02_c1) {
		this.chapter02_c1 = chapter02_c1;
	}


	@Override
	public void run() {
		chapter02_c1.printB();
	}
}

class Chapter02_C1_03 {

	/**
	 * synchronized应用在静态方法中,这样写是对当前.java文件对应的class类进行持锁 synchronized应用在非静态方法中,这样是对对象上锁
	 * 从执行效果来看，synchronized加到静态方法和加到非静态方法是一样的
	 * result:
	 * begin printA threadName = TheadA 1666228792978
	 * end printA threadName = TheadA 1666228795978
	 * begin printB threadName = ThreadB 1666228795979
	 * end printB threadName = ThreadB 1666228798980
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_C1 chapter02_c1 = new Chapter02_C1();

		Chapter02_C1_01 chapter02_c1_01 = new Chapter02_C1_01(chapter02_c1);
		chapter02_c1_01.setName("TheadA");
		chapter02_c1_01.start();

		Chapter02_C1_02 chapter02_c1_02 = new Chapter02_C1_02(chapter02_c1);
		chapter02_c1_02.setName("ThreadB");
		chapter02_c1_02.start();
	}
}