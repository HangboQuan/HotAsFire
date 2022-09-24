package chapter01;

/**
 * @author quanhangbo
 * @date 22-9-24 下午8:21
 */
public class Chapter01_A9 extends Thread {

	@Override
	public void run() {

		/**
		 * sleep()作用是让当前正在执行(即Thread.currentThread())的线程休眠(暂停执行)
		 */
		try {
			System.out.println("run threadName = " + Thread.currentThread().getName() + " begin");
			Thread.sleep(2000);
			System.out.println("run threadName = " + Thread.currentThread().getName() + " end");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Chapter01_A9 chapter01_a9 = new Chapter01_A9();
		System.out.println("begin  = " + System.currentTimeMillis());
		/**
		 * begin  = 1664022480567
		 * run threadName = main begin
		 * run threadName = main end
		 * end = 1664022482568
		 */
//		chapter01_a9.run();

		/**
		 * begin  = 1664022634342
		 * end = 1664022634342
		 * run threadName = Thread-0 begin
		 * run threadName = Thread-0 end
		 */
		chapter01_a9.start();
		System.out.println("end = " + System.currentTimeMillis());

		Thread thread = Thread.currentThread();

		// getId()是取得线程的唯一标识
		System.out.println(thread.getName() + " " + thread.getId());
		System.out.println(chapter01_a9.getName() + " " + chapter01_a9.getId());
	}

}
