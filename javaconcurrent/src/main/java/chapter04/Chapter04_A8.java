package chapter04;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2023/1/2 22:27
 */
public class Chapter04_A8 {
	private ReentrantLock lock = new ReentrantLock();
	public void methodA() {
		lock.lock();
		try {
			System.out.println("methodA getHoldCount=" + lock.getHoldCount());
			methodB();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void methodB() {
		lock.lock();
		try {
			System.out.println("methodB getHoldCount=" + lock.getHoldCount());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A8_01 {
	/**
	 * getHoldCount(): 查询当前线程保持此锁定的个数，也即调用lock()的次数 但是这个方法的实际意义是什么?
	 * methodA getHoldCount=1
	 * methodB getHoldCount=2
	 */
	public static void main(String[] args) {
		Chapter04_A8 chapter04_a8 = new Chapter04_A8();
		chapter04_a8.methodA();
	}
}

class Chapter04_A8_02 {
	// getQueueLength()：返回正等待获取此锁的线程估计数
	// return the estimate of number of threads waiting for this lock
	// 如果有5个线程，1个线程调用了await(), 调用getQueueLength()返回值为4 也即有4个线程同时在等待lock的释放
	
	public ReentrantLock lock = new ReentrantLock();
	public void method() {
		lock.lock();
		try {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + "进入方法");
			Thread.sleep(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A8_03 {

	/**
	 * ThreadName = Thread-1进入方法
	 * 正在等待锁的线程个数为:9
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter04_A8_02 chapter04_a8_02 = new Chapter04_A8_02();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				chapter04_a8_02.method();
			}
		};
		
		Thread[] threads = new Thread[10];
		for(int i = 0; i < threads.length; i ++ ) {
			threads[i] = new Thread(runnable);
		}
		
		for(int i = 0; i < threads.length; i ++ ) {
			threads[i].start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("正在等待锁的线程个数为:" + chapter04_a8_02.lock.getQueueLength());
	}
}

class Chapter04_A8_04 {
	// getWaitQueueLength(Condition condition):返回等待与此锁定相关的给定条件Condition的线程估计数
	// return an estimate of the number of threads waiting on giving condition associated with this lock.
	// 如果有5个线程 每个线程都执行了同一个condition对象的await() 则调用getWaitQueueLength(Condition condition)
	// 方法时的返回值为5
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void waitMethod() {
		lock.lock();
		try {
			condition.await();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void notifyMethod() {
		lock.lock();
		try {
			System.out.println("有" + lock.getWaitQueueLength(condition) + "个线程正在等待condition");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A8_05 {
	public static void main(String[] args) {
		Chapter04_A8_04 chapter04_a8_04 = new Chapter04_A8_04();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				chapter04_a8_04.waitMethod();
			}
		};
		
		Thread[] threadArray = new Thread[10];
		for(int i = 0; i < 10; i ++ ) {
			threadArray[i] = new Thread(runnable);
		}
		
		for(int i = 0; i < 10; i ++ ) {
			threadArray[i].start();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		chapter04_a8_04.notifyMethod();
	}
}
