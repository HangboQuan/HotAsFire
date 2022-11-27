package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-27 下午7:25
 */
public class Chapter03_C8 {
	public static ThreadLocal threadLocal = new ThreadLocal();
}

class Chapter03_C8_01 extends Thread {

	@Override
	public void run() {
		try {
			for(int i = 0; i < 100; i ++ ) {
				Chapter03_C8.threadLocal.set(i + 1);
				System.out.println("ThreadName = " + Thread.currentThread().getName() + Chapter03_C8.threadLocal.get());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


class Chapter03_C8_02 extends Thread {

	@Override
	public void run() {

		try {
			for(int i = 0; i < 100; i ++ ) {
				Chapter03_C8.threadLocal.set((i + 1));
				System.out.println("ThreadName = " + Thread.currentThread().getName() + Chapter03_C8.threadLocal.get());
				Thread.sleep(300);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_C8_03 {


	/**
	 * 可以看到3个线程的值是隔离的
	 * ThreadName = ThreadA1
	 * ThreadName = main1
	 * ThreadName = ThreadB1
	 * ThreadName = ThreadA2
	 * ThreadName = main2
	 * ThreadName = ThreadB2
	 * ThreadName = ThreadA3
	 * ThreadName = main3
	 * ThreadName = ThreadA4
	 * ThreadName = ThreadB3
	 * ThreadName = main4
	 * ThreadName = ThreadA5
	 * ThreadName = main5
	 * ThreadName = ThreadB4
	 * ThreadName = ThreadA6
	 * ThreadName = main6
	 * ThreadName = ThreadA7
	 * ThreadName = ThreadB5
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_C8_01 chapter03_c8_01 = new Chapter03_C8_01();
		Chapter03_C8_02 chapter03_c8_02 = new Chapter03_C8_02();

		chapter03_c8_01.setName("ThreadA");
		chapter03_c8_02.setName("ThreadB");

		chapter03_c8_01.start();
		chapter03_c8_02.start();

		try {
			for(int i = 0; i < 100; i ++ ) {
				Chapter03_C8.threadLocal.set((i + 1));
				System.out.println("ThreadName = " + Thread.currentThread().getName() + Chapter03_C8.threadLocal.get());
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}