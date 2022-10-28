package chapter02;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author quanhangbo
 * @date 22-10-28 下午7:34
 */
public class Chapter02_D6 extends Thread {

	/**
	 * 除了使用synchronized同步锁来保证原子性，还可用AtomicInteger等Atom-原子类来保证原子性
	 *
	 * 原子操作是不可分割的整体，没有其他线程能中断或检查正在原子操作的变量
	 *
	 *
	 */
	private AtomicInteger count = new AtomicInteger(0);

	/**
	 * result: 只截取一段来看，这里有个问题是 并不是是A线程从0 到 10000， B线程从 10001 到 20000， C线程从 20001 到 30000，而是
	 * A, B, C 3个线程之间交替打印，原因是: 虽然Atomic-是原子操作，但是调用run()的操作 方法和方法之间调用确实随机的， 如果要解决这个问题
	 * 就需要同步
	 * ThreadName = ThreadA 1
	 * ThreadName = ThreadC 3
	 * ThreadName = ThreadB 2
	 * ThreadName = ThreadC 5
	 * ThreadName = ThreadA 4
	 * ThreadName = ThreadC 7
	 * ThreadName = ThreadB 6
	 * ThreadName = ThreadC 9
	 * ThreadName = ThreadA 8
	 * ThreadName = ThreadC 11
	 * ThreadName = ThreadB 10
	 */
	@Override
	public void run() {
		for(int i = 0; i < 10000; i ++  ) {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + count.incrementAndGet());
		}
	}
}

class Chapter02_D6_01 {

	public static void main(String[] args) {
		Chapter02_D6 chapter02_d6 = new Chapter02_D6();

		Thread threadA = new Thread(chapter02_d6);
		threadA.setName("ThreadA");
		threadA.start();

		Thread threadB = new Thread(chapter02_d6);
		threadB.setName("ThreadB");
		threadB.start();

		Thread threadC = new Thread(chapter02_d6);
		threadC.setName("ThreadC");
		threadC.start();
	}
}

class Chapter02_D6_02 {

	public static AtomicLong count = new AtomicLong();


	/**
	 * 加上synchronized之后: 这个才是正确的结果 同步执行
	 * Thread-0 加了100之后的值: 100
	 * Thread-3 加了100之后的值: 201
	 * Thread-4 加了100之后的值: 302
	 * Thread-2 加了100之后的值: 403
	 * Thread-1 加了100之后的值: 504
	 * 505
	 *
	 * 不加synchronized 并不能保证顺序 是异步执行的
	 * Thread-0 加了100之后的值: 200
	 * Thread-2 加了100之后的值: 500
	 * Thread-4 加了100之后的值: 400
	 * Thread-3 加了100之后的值: 300
	 * Thread-1 加了100之后的值: 100
	 * 505
	 */
	synchronized public void addCount() {
		System.out.println(Thread.currentThread().getName() + " 加了100之后的值: " + count.addAndGet(100));
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count.addAndGet(1);
	}
}

class Chapter02_D6_03 extends Thread {

	private Chapter02_D6_02 chapter02_d6_02;


	public Chapter02_D6_03(Chapter02_D6_02 chapter02_d6_02) {
		this.chapter02_d6_02 = chapter02_d6_02;
	}

	@Override
	public void run() {
		chapter02_d6_02.addCount();
	}
}


class Chapter02_D6_04 {

	public static void main(String[] args) {
		Chapter02_D6_02 chapter02_d6_02 = new Chapter02_D6_02();

		Chapter02_D6_03[] chapter02_d6_03s = new Chapter02_D6_03[5];

		for(int i = 0; i < chapter02_d6_03s.length; i ++ ) {
			chapter02_d6_03s[i] = new Chapter02_D6_03(chapter02_d6_02);
		}


		for(int i = 0; i < chapter02_d6_03s.length; i ++ ) {
			chapter02_d6_03s[i].start();
		}

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(chapter02_d6_02.count.get());
	}
}
