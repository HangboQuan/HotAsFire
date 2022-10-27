package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-27 下午3:20
 */
public class Chapter02_D2 {

	private String lock = "123";

	/**
	 * 持有相同的锁就是同步的，持有不同的锁就是异步的
	 *
	 * 如果两个线程争抢的锁是123,那么则是同步的; 如果两个线程争抢的锁是不同的,1个是123另一个是456的话,就是异步执行的
	 * result:
	 * get into testMethod ThreadName:ThreadA 1666855883300
	 * get into testMethod ThreadName:ThreadB 1666855883305
	 * get out testMethod ThreadName:ThreadA 1666855888300
	 * get out testMethod ThreadName:ThreadB 1666855888305
	 */
	public void testMethod() {
		synchronized (lock) {
			System.out.println("get into testMethod ThreadName:" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			lock = "456";
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("get out testMethod ThreadName:" + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		}

	}
}

class Chapter02_D2_01 extends Thread {

	private Chapter02_D2 chapter02_d2;

	public Chapter02_D2_01(Chapter02_D2 chapter02_d2) {
		this.chapter02_d2 = chapter02_d2;
	}


	@Override
	public void run() {
		chapter02_d2.testMethod();
	}

}


class Chapter02_D2_02 extends Thread {

	private Chapter02_D2 chapter02_d2;

	public Chapter02_D2_02(Chapter02_D2 chapter02_d2) {
		this.chapter02_d2 = chapter02_d2;
	}


	@Override
	public void run() {
		chapter02_d2.testMethod();
	}

}

class Chapter02_D2_03 {

	public static void main(String[] args) {
		Chapter02_D2 chapter02_d2 = new Chapter02_D2();

		Chapter02_D2_01 chapter02_d2_01 = new Chapter02_D2_01(chapter02_d2);
		chapter02_d2_01.setName("ThreadA");


		Chapter02_D2_02 chapter02_d2_02 = new Chapter02_D2_02(chapter02_d2);
		chapter02_d2_02.setName("ThreadB");

//		chapter02_d2_01.start();

		/**
		 * 如果注释掉 下面的sleep,则ThreadA和ThreadB是同时获取123锁，所以呈现同步效果
		 * result:
		 * get into testMethod ThreadName:ThreadA 1666856537144
		 * get out testMethod ThreadName:ThreadA 1666856542144
		 * get into testMethod ThreadName:ThreadB 1666856542145
		 * get out testMethod ThreadName:ThreadB 1666856547145
		 *
		 * 如果不注释 则ThreadA和ThreadB争抢的是不同的锁，123锁和456锁，所以呈现异步效果
		 * result:
		 * get into testMethod ThreadName:ThreadA 1666856787654
		 * get into testMethod ThreadName:ThreadB 1666856787754
		 * get out testMethod ThreadName:ThreadA 1666856792654
		 * get out testMethod ThreadName:ThreadB 1666856792754
		 */
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		chapter02_d2_02.start();

		// 另外的几种写法:

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_d2.testMethod();
			}
		}, "ThreadC");

		Thread threadD = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter02_d2.testMethod();
			}
		}, "ThreadD");


//		threadC.start();
//		try {
//			Thread.sleep(100);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		threadD.start();


		Runnable runnableE = () -> {
			chapter02_d2.testMethod();
		};

		Runnable runnableF = () -> {
			chapter02_d2.testMethod();
		};

		Thread threadE = new Thread(runnableE);
		Thread threadF = new Thread(runnableF);
		threadE.setName("ThreadE");
		threadF.setName("ThreadF");
		threadE.start();
		try {
			Thread.sleep(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadF.start();

	}
}
