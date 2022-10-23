package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-23 下午8:29
 */
public class Chapter02_C9 {

	static class Chapter02_C9_01 {
		public void methodA() {
			synchronized ("其他的锁") {
				for(int i = 1; i <= 10; i ++ ){
					System.out.println(Thread.currentThread().getName() + " i= " + i);

					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		public synchronized void methodB() {
			for(int i = 11; i <= 20; i ++ ) {
				System.out.println(Thread.currentThread().getName() + " i= " + i);

				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Chapter02_C9_02 {

	/**
	 * 还是异步执行，原因是synchronized持有不同的对象监视器，因此是异步的
	 * result:
	 * A i= 1
	 * B i= 11
	 * A i= 2
	 * B i= 12
	 * A i= 3
	 * B i= 13
	 * A i= 4
	 * B i= 14
	 * A i= 5
	 * B i= 15
	 * A i= 6
	 * B i= 16
	 * A i= 7
	 * B i= 17
	 * A i= 8
	 * B i= 18
	 * A i= 9
	 * B i= 19
	 * A i= 10
	 * B i= 20
	 * @param args
	 */
	public static void main(String[] args) {
		final Chapter02_C9.Chapter02_C9_01 chapter02_c9_01 = new Chapter02_C9.Chapter02_C9_01();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_c9_01.methodA();
			}
		}, "A");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_c9_01.methodB();
			}
		}, "B");

		t1.start();
		t2.start();


	}
}
