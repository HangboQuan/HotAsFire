package chapter01;

/**
 * @author quanhangbo
 * @date 22-9-26 下午2:17
 */
public class Chapter01_B0 extends Thread {


	/**
	 * 停止线程的3中方法:
	 * 1. 线程正常结束，即run运行结束
	 * 2. 使用stop()强行停止，和suspend()/resume()一样都是废弃方法，可能有不可预料的结果(这个不可预料的结果指的是什么)
	 * 3. 使用interrupt()停止线程
	 */

	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 500000; i ++ ) {
			System.out.println("i = " + i);
		}
	}


	/**
	 * interrupt(): interrupts this thread  void
	 * interrupted(): Tests whether the current thread has been interrupted  static boolean
	 * isInterrupted(): Tests whether this thread has been interrupted   boolean
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter01_B0 chapter01_b0 = new Chapter01_B0();
			chapter01_b0.start();
			Thread.sleep(1000);

			/**
			 * 但是在这个例子中，即使调用了interrupt()，但是也未能及时的终止线程
			 */
			chapter01_b0.interrupt();
			System.out.println("Thread interrupted A ?:" + chapter01_b0.interrupted());
			System.out.println("Thread interrupted B ?:" + chapter01_b0.interrupted());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
