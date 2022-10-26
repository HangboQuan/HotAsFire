package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-26 下午10:01
 */
public class Chapter02_D1 {

	static class Chapter02_D1_01 {

		public void methodA(Chapter02_D1_02 chapter02_d1_02) {

			String threadName = Thread.currentThread().getName();
			synchronized (chapter02_d1_02) {
				System.out.println(threadName + " 进入Chapter02_D1_01类中的methodA方法");

				for(int i = 0; i < 10; i ++ ) {
					System.out.println("i = " + i);
					try {
						Thread.sleep(100);
					} catch (Exception e) {

					}
				}
				System.out.println(threadName + " 离开Chapter02_D1_01类中的methodA方法");
			}
		}

		public synchronized void methodB() {
			String threadName = Thread.currentThread().getName();

			System.out.println(threadName + " 进入Chapter02_D1_01类中的methodB方法");

			for(int j = 0; j < 10; j ++ ) {
				System.out.println("j = " + j);
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}
			System.out.println(threadName + " 离开Chapter02_D1_01类中的methodB方法");

		}
	}

	static class Chapter02_D1_02 {

		public synchronized void methodA() {
			String threadName = Thread.currentThread().getName();
			System.out.println(threadName + " 进入Chapter02_D1_02类中的methodA方法");

			for(int k = 0; k < 10; k ++ ) {
				System.out.println("k = " + k);
				try {
					Thread.sleep(100);
				} catch (Exception e) {

				}
			}

			System.out.println(threadName + " 离开Chapter02_D1_02类中的methodB方法");
		}
	}
}


class Chapter02_D1_03 {

	public static void main(String[] args) {

		final Chapter02_D1.Chapter02_D1_01 chapter02_d1_01 = new Chapter02_D1.Chapter02_D1_01();
		final Chapter02_D1.Chapter02_D1_02 chapter02_d1_02 = new Chapter02_D1.Chapter02_D1_02();

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_d1_01.methodA(chapter02_d1_02);
			}
		}, "ThreadA");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_d1_01.methodB();
			}
		}, "ThreadB");


		Thread t3 = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_d1_02.methodA();
			}
		}, "ThreadC");

		t1.start();
		t2.start();
		t3.start();

	}
}