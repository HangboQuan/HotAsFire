package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 下午8:19
 */
public class Chapter02_B4 {

	public void otherMethod() {
		System.out.println("run other method");
	}

	public void doLongTaskTimeMethod() {
		synchronized (this) {
			for(int i = 0; i < 1000; i ++ ) {
				System.out.println("synchronized threadName = " + Thread.currentThread().getName() + " value = " + i);
			}
		}
	}
}

class Chapter02_B4_01 extends Thread {

	private Chapter02_B4 chapter02_b4;

	public Chapter02_B4_01(Chapter02_B4 chapter02_b4) {
		this.chapter02_b4 = chapter02_b4;
	}

	@Override
	public void run() {

		chapter02_b4.doLongTaskTimeMethod();
	}

}


class Chapter02_B4_02 extends Thread {

	private Chapter02_B4 chapter02_b4;

	public Chapter02_B4_02(Chapter02_B4 chapter02_b4) {
		this.chapter02_b4 = chapter02_b4;
	}

	@Override
	public void run() {

		chapter02_b4.otherMethod();
	}

}

class Chapter02_B4_03 {
	public static void main(String[] args) {

		/**
		 * 异步执行，如果要实现同步效果的话，需要给加synchronized方法
		 */
		Chapter02_B4 chapter02_b4 = new Chapter02_B4();

		Chapter02_B4_01 chapter02_b4_01 = new Chapter02_B4_01(chapter02_b4);
		chapter02_b4_01.setName("ThreadA");
		chapter02_b4_01.start();

		Chapter02_B4_02 chapter02_b4_02 = new Chapter02_B4_02(chapter02_b4);
		chapter02_b4_02.setName("ThreadB");
		chapter02_b4_02.start();
	}
}

