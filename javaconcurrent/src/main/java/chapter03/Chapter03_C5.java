package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-26 下午8:42
 */
public class Chapter03_C5 extends Thread {

	@Override
	public void run() {
		try {
			System.out.println(" chapter03_c5 begin time = " + System.currentTimeMillis());
			Thread.sleep(8000);
			System.out.println(" chapter03_c5 end time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void serviceMethod() {
		System.out.println("timer = " + System.currentTimeMillis());
	}
}

class Chapter03_C5_01 extends Thread {
	private Chapter03_C5 chapter03_c5;

	public Chapter03_C5_01(Chapter03_C5 chapter03_c5) {
		this.chapter03_c5 = chapter03_c5;
	}

	@Override
	public void run() {
		synchronized (chapter03_c5) {
			try {
				chapter03_c5.start();
//				Thread.sleep(5000);

				chapter03_c5.join();
				for(int i = 0; i < Integer.MAX_VALUE; i ++ ) {
					String newString = new String();
					Math.random();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}

class Chapter03_C5_02 extends Thread {

	private Chapter03_C5 chapter03_c5;

	public Chapter03_C5_02(Chapter03_C5 chapter03_c5) {
		this.chapter03_c5 = chapter03_c5;
	}

	@Override
	public void run() {
		chapter03_c5.serviceMethod();
	}
}

class Chapter03_C5_03 {

	/**
	 * 只有当synchronized过了5s之后，才释放了chapter03_c5锁，这样才能调用serviceMethod() when Thread.sleep(5000)
	 *  chapter03_c5 begin time = 1669467324154
	 * timer = 1669467329154
	 *  chapter03_c5 end time = 1669467332154
	 *
	 *
	 *  when (chapter03_c5.join()的时候) 证明是释放锁的，在main线程等待了2s之后，锁被释放之后，就立即而执行serviceMethod
	 *   chapter03_c5 begin time = 1669467541594
	 * timer = 1669467543595
	 *  chapter03_c5 end time = 1669467549595
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			Chapter03_C5 chapter03_c5 = new Chapter03_C5();

			Chapter03_C5_01 chapter03_c5_01 = new Chapter03_C5_01(chapter03_c5);
			chapter03_c5_01.start();

			Thread.sleep(2000);
			Chapter03_C5_02 chapter03_c5_02 = new Chapter03_C5_02(chapter03_c5);
			chapter03_c5_02.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}