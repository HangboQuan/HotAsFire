package chapter04;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2023/1/2 22:07
 */
public class Chapter04_A7 {
	
	private boolean isFair;
	private ReentrantLock lock;
	public Chapter04_A7(boolean isFair) {
		this.isFair = isFair;
		lock = new ReentrantLock(isFair);
	}
	
	public void method() {
		lock.lock();
		try {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + "获取到锁");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
//			System.out.println("ThreadName = " + Thread.currentThread().getName() + "释放锁");
		}
	}
}

class Chapter04_A7_01 {

	// ReentrantLock的构造方法中可以指定公平锁和非公平锁，公平锁指的是线程加锁顺序和线程获取锁顺序基本一致
	// 也即先进先出(但不是绝对的)，非公平锁还是随机获取锁
	// 如下结果：公平锁基本加锁和获取锁的顺序是一致的，非公平锁基本加锁和获取锁的顺序基本是乱序
	/**
	 * when isFair equals true
	 * result:
	 * ThreadName Thread-1开始运行
	 * ThreadName Thread-4开始运行
	 * ThreadName = Thread-4获取到锁
	 * ThreadName Thread-0开始运行
	 * ThreadName Thread-5开始运行
	 * ThreadName Thread-8开始运行
	 * ThreadName Thread-7开始运行
	 * ThreadName Thread-2开始运行
	 * ThreadName Thread-9开始运行
	 * ThreadName = Thread-1获取到锁
	 * ThreadName Thread-3开始运行
	 * ThreadName Thread-6开始运行
	 * ThreadName = Thread-0获取到锁
	 * ThreadName = Thread-5获取到锁
	 * ThreadName = Thread-8获取到锁
	 * ThreadName = Thread-7获取到锁
	 * ThreadName = Thread-2获取到锁
	 * ThreadName = Thread-9获取到锁
	 * ThreadName = Thread-3获取到锁
	 * ThreadName = Thread-6获取到锁
	 *
	 *
	 * when isFair equals false
	 * ThreadName Thread-3开始运行
	 * ThreadName Thread-8开始运行
	 * ThreadName Thread-7开始运行
	 * ThreadName Thread-1开始运行
	 * ThreadName = Thread-1获取到锁
	 * ThreadName Thread-9开始运行
	 * ThreadName Thread-6开始运行
	 * ThreadName Thread-5开始运行
	 * ThreadName Thread-0开始运行
	 * ThreadName = Thread-3获取到锁
	 * ThreadName Thread-4开始运行
	 * ThreadName Thread-2开始运行
	 * ThreadName = Thread-4获取到锁
	 * ThreadName = Thread-5获取到锁
	 * ThreadName = Thread-6获取到锁
	 * ThreadName = Thread-7获取到锁
	 * ThreadName = Thread-0获取到锁
	 * ThreadName = Thread-8获取到锁
	 * ThreadName = Thread-9获取到锁
	 * ThreadName = Thread-2获取到锁
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter04_A7 chapter04_a7 = new Chapter04_A7(false);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				System.out.println("ThreadName " + Thread.currentThread().getName() + "开始运行");
				chapter04_a7.method();
			}
		};
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i < threads.length; i ++ ) {
			threads[i] = new Thread(runnable);
		}
		
		for(int i = 0; i < threads.length; i ++ ) {
			threads[i].start();
		}
	}
	
}
