package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 下午7:52
 */
public class Chapter02_B2 {

	public void serviceTask(){
		for(int i = 0; i < 100; i ++ ) {
			System.out.println("no synchronized threadName " + Thread.currentThread().getName() + " value = " + i);
		}

		synchronized (this) {
			for(int i = 0; i < 100; i ++ ) {
				System.out.println("synchronized threadName " + Thread.currentThread().getName() + "value = " + i);
			}
		}
	}
}

class Chapter02_B2_01 extends Thread {

	private Chapter02_B2 chapter02_b2;

	public Chapter02_B2_01(Chapter02_B2 chapter02_b2) {
		this.chapter02_b2 = chapter02_b2;
	}

	@Override
	public void run() {
		chapter02_b2.serviceTask();
	}
}

class Chapter02_B2_02 extends Thread {

	private Chapter02_B2 chapter02_b2;

	public Chapter02_B2_02(Chapter02_B2 chapter02_b2) {
		this.chapter02_b2 = chapter02_b2;
	}

	@Override
	public void run() {
		chapter02_b2.serviceTask();
	}
}

class Chapter02_B2_03 extends Thread {

	/**
	 * 结论: 不加synchronized锁的代码是异步执行的，但是加synchronized的代码是同步执行的
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_B2 chapter02_b2 = new Chapter02_B2();
		Chapter02_B2_01 chapter02_b2_01 = new Chapter02_B2_01(chapter02_b2);
		chapter02_b2_01.setName("ThreadA");
		chapter02_b2_01.start();
		Chapter02_B2_02 chapter02_b2_02 = new Chapter02_B2_02(chapter02_b2);
		chapter02_b2_02.setName("ThreadB");
		chapter02_b2_02.start();
	}

}