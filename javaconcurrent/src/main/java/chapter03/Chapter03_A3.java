package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-7 上午9:07
 */
public class Chapter03_A3 {

	/**
	 * 加volatile保证性， 如果不加volatile
	 */
	private volatile List list = new ArrayList<>();

	public void add() {
		list.add("quanhangbo");
	}

	public int size() {
		return list.size();
	}

}

class Chapter03_A3_01 extends Thread {


	private Chapter03_A3 chapter03_a3;

	public Chapter03_A3_01(Chapter03_A3 chapter03_a3) {
		this.chapter03_a3 = chapter03_a3;
	}

	@Override
	public void run() {
		for(int i = 0; i < 10; i ++ ) {
			try {
				chapter03_a3.add();
				System.out.println("List中添了了一个元素");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
}

class Chapter03_A3_02 extends Thread {


	private Chapter03_A3 chapter03_a3;

	public Chapter03_A3_02(Chapter03_A3 chapter03_a3) {
		this.chapter03_a3 = chapter03_a3;
	}

	@Override
	public void run() {

		try {
			while(true) {
				if(chapter03_a3.size() == 5) {
					System.out.println("list size equals 5");
					throw new InterruptedException();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_A3_03 {

	public static void main(String[] args) {

		Chapter03_A3 chapter03_a3 = new Chapter03_A3();

		Chapter03_A3_01 chapter03_a3_01 = new Chapter03_A3_01(chapter03_a3);
		chapter03_a3_01.setName("ThreadA");
		chapter03_a3_01.start();

		Chapter03_A3_02 chapter03_a3_02 = new Chapter03_A3_02(chapter03_a3);
		chapter03_a3_02.setName("ThreadB");
		chapter03_a3_02.start();
	}
}

class Chapter03_A3_04 {

	private static List list = new ArrayList();
	public static void add() {
		list.add("anyString");
	}

	public static int size() {
		return list.size();
	}
}


class Chapter03_A3_05 extends Thread {

	private Object lock;

	public Chapter03_A3_05(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				if(Chapter03_A3_04.size() != 5) {
					System.out.println("wait begin " + System.currentTimeMillis());
					lock.wait();
					System.out.println("wait end " + System.currentTimeMillis());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


class Chapter03_A3_06 extends Thread {
	private Object lock;

	public Chapter03_A3_06(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				for(int i = 0; i < 10; i ++ ) {
					Chapter03_A3_04.add();
					if(Chapter03_A3_04.size() == 5) {
						lock.notify();
						System.out.println("已经发出通知！");
					}
					System.out.println("添加了" + (i + 1) + "个元素");
					Thread.sleep(1000);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_A3_07 {

	public static void main(String[] args) {

		try {
			Object lock = new Object();
			Chapter03_A3_05 chapter03_a3_05 = new Chapter03_A3_05(lock);
			chapter03_a3_05.start();

			Thread.sleep(50);

			Chapter03_A3_06 chapter03_a3_06 = new Chapter03_A3_06(lock);



			chapter03_a3_06.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}