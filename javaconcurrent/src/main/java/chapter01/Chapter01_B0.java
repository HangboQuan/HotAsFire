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
		for(int i = 0; i < 50000; i ++ ) {
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
			// result:
			/**
			 * 从结果来看线程并未停止，interrupted(): 测试当前线程是否已经中断，这个当前线程就是main,从未中断过
			 * Thread interrupted A ?:false
			 * Thread interrupted B ?:false
			 */
			System.out.println("Thread interrupted A ?:" + chapter01_b0.getName() + " " + chapter01_b0.interrupted());
			System.out.println("Thread interrupted B ?:" + chapter01_b0.interrupted());

			Thread.currentThread().interrupt();

			/**
			 * result:
			 * main 疑问: 竟然终止了main主线程，为什么还能继续执行下去?
			 * ThreadA interrupted ?:true
			 * ThreadB interrupted ?:false
			 */
			System.out.println(Thread.currentThread().getName());

			/**
			 * interrupted():
			 * Tests whether the current thread has been interrupted. The interrupted status of the thread is cleared by this method.
			 * In other words, if this method were to be called twice in succession, the second call would return false(unless the thread
			 * current were interrupted again, after the first cleared its interrupted status and before the second call had examined it)
			 *
			 * 测试当前线程是否中断，线程中断状态由该方法清除，如果连续两次调用该方法，则第二次调用返回false(在第一次调用清除了其中断状态之后，且第二次调用检验完中断状态之前)
			 */
			System.out.println("ThreadA interrupted ?:" + Thread.interrupted());
			System.out.println("ThreadB interrupted ?:" + Thread.interrupted());
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
