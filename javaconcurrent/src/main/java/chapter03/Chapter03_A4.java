package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 上午9:34
 */
public class Chapter03_A4 {

	/**
	 * 验证了wait是释放锁
	 * 如果这里改为sleep，不释放锁，即同步效果
	 */
	public void serviceMethod(Object lock){
		try {
			synchronized (lock) {
				System.out.println("begin wait = " + System.currentTimeMillis());
				lock.wait();
				System.out.println("end wait = " + System.currentTimeMillis());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_A4_01 extends Thread {

	private Chapter03_A4 chapter03_a4;

	public Chapter03_A4_01(Chapter03_A4 chapter03_a4) {
		this.chapter03_a4 = chapter03_a4;
	}

	@Override
	public void run() {
		Object lock = new Object();
		chapter03_a4.serviceMethod(lock);
	}
}

class Chapter03_A4_02 extends Thread {

	private Chapter03_A4 chapter03_a4;

	public Chapter03_A4_02(Chapter03_A4 chapter03_a4) {
		this.chapter03_a4 = chapter03_a4;
	}

	@Override
	public void run() {
		Object lock = new Object();
		chapter03_a4.serviceMethod(lock);
	}
}

class Chapter03_A4_03 {


	public static void main(String[] args) {
		Chapter03_A4 chapter03_a4 = new Chapter03_A4();

		Chapter03_A4_01 chapter03_a4_01 = new Chapter03_A4_01(chapter03_a4);
		chapter03_a4_01.start();

		Chapter03_A4_02 chapter03_a4_02 = new Chapter03_A4_02(chapter03_a4);
		chapter03_a4_02.start();
	}
}