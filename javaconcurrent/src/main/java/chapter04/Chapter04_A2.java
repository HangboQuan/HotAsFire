package chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/19 20:42
 */
public class Chapter04_A2 {
	/**
	 * ReentrantLock实现等待通知 需要借助Condition
	 * Condition可以实现多路通知功能，在一个lock对象中创建多个condition实例 线程对象可以注册指定的condition中
	 * 从而有选择的进行线程通知，在调度线程的时候更加灵活
	 */
	private Lock lock = new ReentrantLock();
	private Condition condition  = lock.newCondition();


	/**
	 * A
	 * Exception in thread "Thread-0" java.lang.IllegalMonitorStateException
	 * 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.addConditionWaiter(AbstractQueuedSynchronizer.java:1888)
	 * 	at java.base/java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2077)
	 * 	at chapter04.Chapter04_A2.await(Chapter04_A2.java:24)
	 * 	at chapter04.Chapter04_A2_01.run(Chapter04_A2.java:44)
	 *
	 * 	如果在调用condition.await()之前，没有调用lock.lock()，就抛出了异常 -> 在调用await() 先获取到lock同步监视器
	 */
	public void await() {
		lock.lock();
		try {
			System.out.println("await时间为 " + System.currentTimeMillis());
			condition.await();
//			System.out.println("B");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			System.out.println("release the lock");
		}

	}
	
	public void signal() {
		lock.lock();
		try {
			System.out.println("signal时间为 " + System.currentTimeMillis());
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A2_01 extends Thread {
	private Chapter04_A2 chapter04_a2;

	public Chapter04_A2_01(Chapter04_A2 chapter04_a2) {
		this.chapter04_a2 = chapter04_a2;
	}

	@Override
	public void run() {
		chapter04_a2.await();
	}
}

class Chapter04_A2_02 {

	/**
	 * Object类的wait() <=> Condition类中的await()
	 * Object类的wait(long timeout) <=> Condition类中的await(long time, TimeUnit unit)
	 * Object类的notify()/notifyAll() <=> Condition类中的signal()/signalAll()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Chapter04_A2 chapter04_a2 = new Chapter04_A2();
		Chapter04_A2_01 chapter04_a2_01 = new Chapter04_A2_01(chapter04_a2);

		chapter04_a2_01.start();
		
		Thread.sleep(3000);
		chapter04_a2.signal();
	}
}
