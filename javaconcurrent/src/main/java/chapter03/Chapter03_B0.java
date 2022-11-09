package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-9 上午9:42
 */
public class Chapter03_B0 {
	public static List list = new ArrayList<>();
}

class Chapter03_B0_01 {

	private String lock;

	public Chapter03_B0_01(String lock) {
		this.lock = lock;
	}

	public void add() {
		synchronized (lock) {
			Chapter03_B0.list.add("anyString");
			lock.notifyAll();
		}
	}
}

class Chapter03_B0_02 {
	private String lock;

	public Chapter03_B0_02(String lock) {
		this.lock = lock;
	}

	public void subtract() {
		try {
			// before
//			synchronized (lock) {
//				if(Chapter03_B0.list.size() == 0) {
//					System.out.println("wait begin ThreadName = " + Thread.currentThread().getName());
//					lock.wait();
//					System.out.println("wait end ThreadName = " + Thread.currentThread().getName());
//				}
//				Chapter03_B0.list.remove(0);
//				System.out.println("list size = " + Chapter03_B0.list.size());
//			}

			// after
			synchronized (lock) {

				// 改成while的原因是 只有当列表是不为空的时候才去执行减操作
				while(Chapter03_B0.list.size() == 0) {
					System.out.println("wait begin ThreadName = " + Thread.currentThread().getName());
					lock.wait();
					System.out.println("wait end ThreadName = " + Thread.currentThread().getName());
				}
				Chapter03_B0.list.remove(0);
				System.out.println("list size = " + Chapter03_B0.list.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B0_03 extends Thread {

	private Chapter03_B0_01 chapter03_b0_01;

	public Chapter03_B0_03(Chapter03_B0_01 chapter03_b0_01) {
		this.chapter03_b0_01 = chapter03_b0_01;
	}

	@Override
	public void run() {
		chapter03_b0_01.add();
	}
}


class Chapter03_B0_04 extends Thread {
	private Chapter03_B0_02 chapter03_b0_02;

	public Chapter03_B0_04(Chapter03_B0_02 chapter03_b0_02) {
		this.chapter03_b0_02 = chapter03_b0_02;
	}

	@Override
	public void run() {
		chapter03_b0_02.subtract();
	}
}

class Chapter03_B0_05 {

	/**
	 *
	 * before result:
	 * wait begin ThreadName = ThreadB
	 * wait begin ThreadName = ThreadC
	 * wait end ThreadName = ThreadB
	 * list size = 0
	 * wait end ThreadName = ThreadC
	 * java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
	 * 	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
	 * 	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
	 * 	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
	 * 	at java.base/java.util.Objects.checkIndex(Objects.java:372)
	 * 	at java.base/java.util.ArrayList.remove(ArrayList.java:535)
	 * 	at chapter03.Chapter03_B0_02.subtract(Chapter03_B0.java:45)
	 * 	at chapter03.Chapter03_B0_04.run(Chapter03_B0.java:78)
	 *
	 * 	after result:
	 * 	wait begin ThreadName = ThreadB
	 *  wait begin ThreadName = ThreadC
	 *  wait end ThreadName = ThreadB
	 *  list size = 0
	 *  wait end ThreadName = ThreadC
	 *  wait begin ThreadName = ThreadC
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * 两个实现remove()操作的线程，在Thread.sleep(2000)之前都执行了wait()
		 *
		 * add()的线程唤醒了所有呈等待状态的线程，唤醒之后B线程和C线程分别区执行remove()操作，add()一次却remove()两次就造成了数组越界
		 */
		try {
			String lock = new String("");

			Chapter03_B0_01 chapter03_b0_01 = new Chapter03_B0_01(lock);
			Chapter03_B0_02 chapter03_b0_02 = new Chapter03_B0_02(lock);

			Chapter03_B0_03 chapter03_b0_03 = new Chapter03_B0_03(chapter03_b0_01);

			Chapter03_B0_04 chapter03_b0_04A = new Chapter03_B0_04(chapter03_b0_02);



			chapter03_b0_04A.setName("ThreadB");
			chapter03_b0_04A.start();


			Chapter03_B0_04 chapter03_b0_04B = new Chapter03_B0_04(chapter03_b0_02);
			chapter03_b0_04B.setName("ThreadC");
			chapter03_b0_04B.start();


			Thread.sleep(2000);

			chapter03_b0_03.setName("ThreadA");
			chapter03_b0_03.start();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}
