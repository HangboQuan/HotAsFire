package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-26 下午10:01
 */
public class Chapter02_D1 {

	/**
	 *
	 * 静态内部类和 单独写出来的类 其实差别不大，这里ThreadA锁的是chapter02_D1_02对象，ThreadB锁的是chapter02_D1_01对象
	 * ThreadC锁的是 chapter02_d1_02对象，因此ThreadA和ThreadC是同步执行的， ThreadA和ThreadB是异步执行的
	 * result:
	 * ThreadA 进入Chapter02_D1_01类中的methodA方法
	 * ThreadB 进入Chapter02_D1_01类中的methodB方法
	 * i = 0
	 * j = 0
	 * j = 1
	 * i = 1
	 * j = 2
	 * i = 2
	 * j = 3
	 * i = 3
	 * j = 4
	 * i = 4
	 * j = 5
	 * i = 5
	 * j = 6
	 * i = 6
	 * j = 7
	 * i = 7
	 * j = 8
	 * i = 8
	 * j = 9
	 * i = 9
	 * ThreadB 离开Chapter02_D1_01类中的methodB方法
	 * ThreadA 离开Chapter02_D1_01类中的methodA方法
	 * ThreadC 进入Chapter02_D1_02类中的methodA方法
	 * k = 0
	 * k = 1
	 * k = 2
	 * k = 3
	 * k = 4
	 * k = 5
	 * k = 6
	 * k = 7
	 * k = 8
	 * k = 9
	 * ThreadC 离开Chapter02_D1_02类中的methodB方法
	 */

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