package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-21 下午7:56
 */
public class Chapter02_C5 {

	synchronized public void methodA() {
		System.out.println("methodA begin");
		boolean isContinueRun = true;
		while (isContinueRun) {

		}
		System.out.println("methodA end");
	}

	synchronized public void methodB() {
		System.out.println("methodB begin");
		System.out.println("methodB end");
	}
}

class Chapter02_C5_01 extends Thread {
	private Chapter02_C5 chapter02_c5;

	public Chapter02_C5_01(Chapter02_C5 chapter02_c5) {
		this.chapter02_c5 = chapter02_c5;
	}

	@Override
	public void run() {
		chapter02_c5.methodA();
	}
}

class Chapter02_C5_02 extends Thread {

	private Chapter02_C5 chapter02_c5;

	public Chapter02_C5_02(Chapter02_C5 chapter02_c5) {
		this.chapter02_c5 = chapter02_c5;
	}

	@Override
	public void run() {
		chapter02_c5.methodB();
	}
}

class Chapter02_C5_03 {

	/**
	 * 运行结果是 methodA begin卡住了 线程B永远得不到运行的机会
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_C5 chapter02_c5 = new Chapter02_C5();

		Chapter02_C5_01 chapter02_c5_01 = new Chapter02_C5_01(chapter02_c5);
		chapter02_c5_01.setName("ThreadA");
		chapter02_c5_01.start();

		Chapter02_C5_02 chapter02_c5_02 = new Chapter02_C5_02(chapter02_c5);
		chapter02_c5_02.setName("ThreadB");
		chapter02_c5_02.start();
	}
}
