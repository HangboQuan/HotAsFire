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

	/**
	 * 这个例子说明: 即使执行notify() 也不是立即释放锁, 不是立即会回到wait()之后继续执行, 而是会先继续执行完notify()同步块的代码, 然后
	 * 才释放锁, 才回到wait()继续执行
	 * result:
	 * wait begin 1667868452460
	 * 添加了1个元素
	 * 添加了2个元素
	 * 添加了3个元素
	 * 添加了4个元素
	 * 添加了5个元素
	 * 已经发出通知！
	 * 添加了6个元素
	 * 添加了7个元素
	 * 添加了8个元素
	 * 添加了9个元素
	 * 添加了10个元素
	 * wait end 1667868462513
	 */
	@Override
	public void run() {
		try {
			synchronized (lock) {
				for(int i = 0; i < 10; i ++ ) {
					Chapter03_A3_04.add();
					System.out.println("添加了" + (i + 1) + "个元素");
					if(Chapter03_A3_04.size() == 5) {
						lock.notify();
						System.out.println("已经发出通知！");
					}

					Thread.sleep(1000);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_A3_07 {

	/**
	 * synchronized()可以将任何一个对象作为同步对象，因为wait()和notify()是Object的方法，它们必须被用在synchronized()同步的Object临界区内
	 * 通过wait()使处于临界区的线程进入阻塞，释放锁; notify()可以唤醒因调用一个wait()而等待锁对象的线程，使其进入就绪状态
	 *
	 * 被唤醒的线程会试图重新获取锁，并继续执行wait()之后的代码，如果执行notify()操作诗没有处于阻塞状态的线程，那么该命令会被忽略
	 *
	 * wait()释放锁 之后会进入等待队列，呈阻塞状态
	 * notify()不会立即释放锁 随机从等待队列中唤醒等待同一个共享资源的线程，使该线程退出等待队列，变为就绪状态
	 * notifyAll()不会立即释放锁 从等待队列中唤醒等待同一个共享资源的所有线程，是所有线程退出等待队列，变为就绪状态
	 *
	 * 每个锁对象都有两个队列，一个是就绪队列，一个是阻塞队列
	 * 就绪队列: 存储了将要获得锁的线程
	 * 阻塞队列: 存储了被阻塞的线程
	 * @param args
	 */
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