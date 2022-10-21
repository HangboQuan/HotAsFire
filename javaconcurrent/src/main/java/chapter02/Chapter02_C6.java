package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-21 下午8:06
 */
public class Chapter02_C6 {

	Object object1 = new Object();
	public void methodA() {
		synchronized (object1) {
			System.out.println("methodA begin");
			boolean isContinueRun = true;
			while (isContinueRun) {

			}
			System.out.println("methodA end");
		}

	}

	Object object2 = new Object();
	public void methodB() {
		synchronized (object2) {
			System.out.println("methodB begin");
			System.out.println("methodB end");
		}

	}
}

class Chapter02_C6_01 extends Thread {
	private Chapter02_C6 chapter02_c6;

	public Chapter02_C6_01(Chapter02_C6 chapter02_c6) {
		this.chapter02_c6 = chapter02_c6;
	}

	@Override
	public void run() {
		chapter02_c6.methodA();
	}
}

class Chapter02_C6_02 extends Thread {

	private Chapter02_C6 chapter02_c6;

	public Chapter02_C6_02(Chapter02_C6 chapter02_c6) {
		this.chapter02_c6 = chapter02_c6;
	}

	@Override
	public void run() {
		chapter02_c6.methodB();
	}
}

class Chapter02_C6_03 {

	/**
	 * 这里锁住的是不同的对象，所以会呈现异步的执行下效果
	 *
	 * result:
	 * methodA begin
	 * methodB begin
	 * methodB end
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_C6 chapter02_c6 = new Chapter02_C6();

		Chapter02_C6_01 chapter02_c6_01 = new Chapter02_C6_01(chapter02_c6);
		chapter02_c6_01.setName("ThreadA");
		chapter02_c6_01.start();

		Chapter02_C6_02 chapter02_c6_02 = new Chapter02_C6_02(chapter02_c6);
		chapter02_c6_02.setName("ThreadB");
		chapter02_c6_02.start();
	}
}

