package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-26 下午8:20
 */
public class Chapter03_C4 extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("begin time = " + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("end time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_C4_01 {

	/**
	 * 在实现效果上，join(2000)和Thread.sleep(2000)是一样的
	 * 区别是：join(long)源码是通过wait(long)实现的，wait()会释放锁; 但是sleep()是不释放锁的
	 * begin time = 1669465831830
	 * finally time = 1669465833830
	 * end time = 1669465836831
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Chapter03_C4 chapter03_c4 = new Chapter03_C4();
			chapter03_c4.start();
//			chapter03_c4.join(2000);
			Thread.sleep(2000);
			System.out.println("finally time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
