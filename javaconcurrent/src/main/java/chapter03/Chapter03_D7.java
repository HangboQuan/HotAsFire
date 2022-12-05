package chapter03;

/**
 * @author quanhangbo
 * @date 2022/12/4 22:48
 */
public class Chapter03_D7 {
	
	// 使用wait()/notify()实现 用两个线程交替打印0~100的奇偶数
	// A线程打印奇数，B线程打印偶数

	private static volatile int count = 1;
	private static Object object = new Object();

	// 打印奇数
	static class ThreadA extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				for(int i = 1; i <= 50; i ++ ) {
					if (count % 2 == 1) {
						// 如果是偶数
						try {
							object.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 重新获得锁
						System.out.println(Thread.currentThread().getName() + "------" + count);
						count ++;
						object.notify();
					}
				}
				
			}
		}
	}
	
	static class ThreadB extends Thread {
		@Override
		public void run() {
			synchronized (object) {
				for(int i = 1; i <= 50; i ++ ) {
					// 如果是奇数
					if (count % 2 == 0) {
						try {
							object.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					// 重新获得锁
					System.out.println(Thread.currentThread().getName() + "------" + count);
					count ++;
					object.notify();
				}
				
			}
		}
		
		
	}


	public static void main(String[] args) {
		ThreadA threadA = new ThreadA();
		ThreadB threadB = new ThreadB();
		
		threadA.start();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadB.start();
	}
	/**
	 * 下面4种情况发生了 Thread才能被唤醒
	 * 1. 另一个线程调用这个对象的notify()且刚好唤醒的是本线程
	 * 2. 另一个线程调用这个对象的notifyAll()
	 * 3. 过了wait(long timeout)的规定时间之后
	 * 4. 线程自身调用了interrupt()
	 */
//	private static Object object = new Object();
	static class Thread1 extends Thread {
		
		@Override
		public void run() {
			synchronized (object) {
				System.out.println(Thread.currentThread().getName() + "开始执行了");
				try {
					object.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + "重新获取了锁");
			}
		}
	}


	static class Thread2 extends Thread {
		
		@Override
		public void run() {
			synchronized (object) {
				try {
					// 这里也验证了wait()释放锁了
					object.notify();
					System.out.println(Thread.currentThread().getName() + "调用了notify()");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}
	
//	public static void main(String[] args) {
//
//		/**
//		 * Thread-0开始执行了
//		 * Thread-1调用了notify()
//		 * Thread-0重新获取了锁
//		 */
//		Thread1 thread1 = new Thread1();
//		Thread2 thread2 = new Thread2();
//
//		thread1.start();
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		thread2.start();
//	}
}
