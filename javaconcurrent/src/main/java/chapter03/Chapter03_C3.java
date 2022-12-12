package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-26 下午8:08
 */
public class Chapter03_C3 extends Thread {
	
	@Override
	public void run() {
		int i = 0;
		for(; i < 200000000; i ++ ) {
			String newString = new String();
			Math.random();
		}
		// 及时join()遇到中断异常抛出了异常，但是仍然不影响调用join()方法的线程，该线程仍然会执行完run()
		System.out.println(i);
	}
}

class Chapter03_C3_01 extends Thread {

	Chapter03_C3 chapter03_c3;

	public Chapter03_C3_01(Chapter03_C3 chapter03_c3) {
		this.chapter03_c3 = chapter03_c3;
	}

	@Override
	public void run() {
		try {
			chapter03_c3.start();
			chapter03_c3.join();
			//	@Override
//	public final void join() throws InterruptedException {
//		join(0);
//	}
//
//	@Override
//	public void join(final long mills) throws InterruptedException {
//		if (mills > 0) {
//			// 当前线程如果存活
//			if (isAlive()) {
//				// 当前的纳秒
//				final long startTime = System.nanoTime();
//				long delay = mills;
//				do {
//					wait(delay);
//					// 只有当线程存活 && 等待时间不足mills时 才会重新执行wait(delay)
//					// delay = (mills - TimeUnit.NANOSECONDS.toMills(System.nanoTime() - startTime)) > 0
//					// 所以这里为什么要用while循环呢？ 理论上只有wait(delay)就可以了？ 难道是因为wait(delay)不准确吗？
//				} while (isAlive() && (delay = mills - TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) > 0);
//			}
//		}
//	}
			System.out.println("等待chapter03_c3执行完,然后再打印该行");
		} catch (Exception e) {
			System.out.println("join 遇见 interrupt, 抛出异常");
			e.printStackTrace();
		}
	}
}

class Chapter03_C3_02 extends Thread {

	Chapter03_C3_01 chapter03_c3_01;

	public Chapter03_C3_02(Chapter03_C3_01 chapter03_c3_01) {
		this.chapter03_c3_01 = chapter03_c3_01;
	}

	@Override
	public void run(){
		chapter03_c3_01.interrupt();
	}
	
}


class Chapter03_C3_03 {

	/**
	 * join 遇见 interrupt, 抛出异常
	 * java.lang.InterruptedException
	 * 	at java.base/java.lang.Object.wait(Native Method)
	 * 	at java.base/java.lang.Thread.join(Thread.java:1308)
	 * 	at java.base/java.lang.Thread.join(Thread.java:1375)
	 * 	at chapter03.Chapter03_C3_01.run(Chapter03_C3.java:30)
	 *
	 * 	虽然抛出了异常，但是在ide中看到是红色按钮，chapter03_c3线程仍然不受影响继续执行
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_C3 chapter03_c3 = new Chapter03_C3();
			Chapter03_C3_01 chapter03_c3_01 = new Chapter03_C3_01(chapter03_c3);
			chapter03_c3_01.start();

			Thread.sleep(500);

			Chapter03_C3_02 chapter03_c3_02 = new Chapter03_C3_02(chapter03_c3_01);
			chapter03_c3_02.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}