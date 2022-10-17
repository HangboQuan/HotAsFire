package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 上午9:32
 */
public class Chapter02_B0 {

	public void serviceMethod() {
		try {
			/**
			 *
			 * 虽然这里设置了同步代码块, 但是效果和synchronized同步方法一样, 并没有明显延迟变化
			 */
			synchronized (this) {
				System.out.println("begin Time = " + System.currentTimeMillis());
				Thread.sleep(2000);
				System.out.println("end Time = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_B0_01 extends Thread {

	private Chapter02_B0 chapter02_b0;

	public Chapter02_B0_01(Chapter02_B0 chapter02_b0) {
		this.chapter02_b0 = chapter02_b0;
	}

	@Override
	public void run() {
		chapter02_b0.serviceMethod();
	}
}

class Chapter02_B0_02 extends Thread {

	private Chapter02_B0 chapter02_b0;

	public Chapter02_B0_02(Chapter02_B0 chapter02_b0) {
		this.chapter02_b0 = chapter02_b0;
	}

	@Override
	public void run() {
		chapter02_b0.serviceMethod();
	}
}

class Chapter02_B0_03 {

	public static void main(String[] args) {
		Chapter02_B0 chapter02_b0 = new Chapter02_B0();

		Chapter02_B0_01 chapter02_b0_01 = new Chapter02_B0_01(chapter02_b0);
		chapter02_b0_01.setName("a");
		chapter02_b0_01.start();
		Chapter02_B0_02 chapter02_b0_02 = new Chapter02_B0_02(chapter02_b0);
		chapter02_b0_02.setName("b");
		chapter02_b0_02.start();
	}
}
