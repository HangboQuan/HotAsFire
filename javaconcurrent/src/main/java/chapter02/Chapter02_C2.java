package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-20 下午8:16
 */
public class Chapter02_C2 {

	/**
	 * 等价于synchronized(Chapter02_C2.class)
	 */
	synchronized public static void printA() {
		try {
			System.out.println("printA begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println("printA end ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void printB() {
		try {
			System.out.println("printB begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(3000);
			System.out.println("printB end ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void printC() {
		try {
			System.out.println("printC begin ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("printC end ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class Chapter02_C2_01 extends Thread {

	private Chapter02_C2 chapter02_c2;

	public Chapter02_C2_01(Chapter02_C2 chapter02_c2) {
		this.chapter02_c2 = chapter02_c2;

	}

	@Override
	public void run() {
		chapter02_c2.printA();
	}

}

class Chapter02_C2_02 extends Thread {

	private Chapter02_C2 chapter02_c2;

	public Chapter02_C2_02(Chapter02_C2 chapter02_c2) {
		this.chapter02_c2 = chapter02_c2;

	}

	@Override
	public void run() {
		chapter02_c2.printB();
	}

}

class Chapter02_C2_03 extends Thread {

	private Chapter02_C2 chapter02_c2;

	public Chapter02_C2_03(Chapter02_C2 chapter02_c2) {
		this.chapter02_c2 = chapter02_c2;

	}

	@Override
	public void run() {
		chapter02_c2.printC();
	}

}


class Chapter02_C2_04 {

	public static void main(String[] args) {

		/**
		 * synchronized锁静态方法的话，其实是锁得是写在哪个类的，就锁的是哪个对象
		 * 从结果来看 methodB和methodC都是同步方法，锁的是调用他的对象
		 * 因此methodA和 methodB/C是异步执行的
		 * result:
		 * printA begin ThreadName = ThreadA 1666269404087
		 * printB begin ThreadName = ThreadB 1666269404087
		 * printA end ThreadName = ThreadA 1666269406088
		 * printB end ThreadName = ThreadB 1666269407088
		 * printC begin ThreadName = ThreadC 1666269407088
		 * printC end ThreadName = ThreadC 1666269412089
		 */
		Chapter02_C2 chapter02_c2 = new Chapter02_C2();

		Chapter02_C2_01 chapter02_c2_01 = new Chapter02_C2_01(chapter02_c2);

		Chapter02_C2_02 chapter02_c2_02 = new Chapter02_C2_02(chapter02_c2);

		Chapter02_C2_03 chapter02_c2_03 = new Chapter02_C2_03(chapter02_c2);

		chapter02_c2_01.setName("ThreadA");
		chapter02_c2_02.setName("ThreadB");
		chapter02_c2_03.setName("ThreadC");

		chapter02_c2_01.start();
		chapter02_c2_02.start();
		chapter02_c2_03.start();
	}
}
