package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-16 下午6:35
 */
public class Chapter02_A8 {
	synchronized public void serviceMethod() {

		try {
			System.out.println("parent class sleep begin threadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("parent class sleep end threadName = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter02_A8_01 extends Chapter02_A8 {

	@Override
	public void serviceMethod() {
		try {
			System.out.println("sub class sleep begin threadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("sub class sleep end threadName = " + Thread.currentThread().getName() + "time = " + System.currentTimeMillis());
			super.serviceMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_A8_02 extends Thread {

	private Chapter02_A8_01 chapter02_a8_01;

	public Chapter02_A8_02(Chapter02_A8_01 chapter02_a8_01) {
		this.chapter02_a8_01 = chapter02_a8_01;
	}


	@Override
	public void run() {
		chapter02_a8_01.serviceMethod();
	}
}


class Chapter02_A8_03 extends Thread {

	private Chapter02_A8_01 chapter02_a8_01;

	public Chapter02_A8_03(Chapter02_A8_01 chapter02_a8_01) {
		this.chapter02_a8_01 = chapter02_a8_01;
	}


	@Override
	public void run() {
		chapter02_a8_01.serviceMethod();
	}
}

class Chapter02_A8_04 {

	public static void main(String[] args) {

		/**
		 * 第一行和第二行 都是异步执行的，也就是说synchronized同步不可以继承
		 * 1,2行同时打印
		 * 3,4,5行同时打印
		 * 6,7行同步打印
		 * 8行打印
		 *
		 * 如果要实现同步的效果，需要给子类的方法也加上synchronized
		 * result:
		 * sub class sleep begin threadName = ThreadA time = 1665917525430
		 * sub class sleep begin threadName = ThreadB time = 1665917525430
		 * sub class sleep end threadName = ThreadBtime = 1665917528430
		 * sub class sleep end threadName = ThreadAtime = 1665917528430
		 * parent class sleep begin threadName = ThreadB time = 1665917528430
		 * parent class sleep end threadName = ThreadBtime = 1665917531431
		 * parent class sleep begin threadName = ThreadA time = 1665917531431
		 * parent class sleep end threadName = ThreadAtime = 1665917534432
		 */
		Chapter02_A8_01 chapter02_a8_01 = new Chapter02_A8_01();

		Chapter02_A8_02 chapter02_a8_02 = new Chapter02_A8_02(chapter02_a8_01);

		chapter02_a8_02.setName("ThreadA");
		chapter02_a8_02.start();
		Chapter02_A8_03 chapter02_a8_03 = new Chapter02_A8_03(chapter02_a8_01);
		chapter02_a8_03.setName("ThreadB");


		chapter02_a8_03.start();

	}
}