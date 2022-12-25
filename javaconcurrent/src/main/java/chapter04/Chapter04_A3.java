package chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/25 22:53
 */
public class Chapter04_A3 {
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	public void awaitA() {
		lock.lock();
		try {
			System.out.println("begin awaitA 时间为 " + System.currentTimeMillis() + " ThreadName = " +
					                  Thread.currentThread().getName());
			condition.await();
			System.out.println("end awaitA 时间为 " + System.currentTimeMillis() + " ThreadName = " +
					                  Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void awaitB() {
		lock.lock();
		try {
			System.out.println("begin awaitB 时间为 " + System.currentTimeMillis() + " ThreadName = " +
					                   Thread.currentThread().getName());
			condition.await();
			System.out.println("end awaitB 时间为 " + System.currentTimeMillis() + " ThreadName = " +
					                   Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAll() {
		lock.lock();
		try {
			System.out.println("signalAll 时间为 " + System.currentTimeMillis() + " ThreadName = " +
					                  Thread.currentThread().getName());
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A3_ThreadA extends Thread {
	private Chapter04_A3 chapter04_a3;
	
	public Chapter04_A3_ThreadA(Chapter04_A3 chapter04_a3) {
		this.chapter04_a3 = chapter04_a3;
	}
	
	@Override
	public void run() {
		chapter04_a3.awaitA();
	}
}

class Chapter04_A3_ThreadB extends Thread {
	private Chapter04_A3 chapter04_a3;
	
	public Chapter04_A3_ThreadB(Chapter04_A3 chapter04_a3) {
		this.chapter04_a3 = chapter04_a3;
	}
	
	@Override
	public void run() {
		chapter04_a3.awaitB();
	}
}

class Chapter04_A3_01 {

	/**
	 * begin awaitB 时间为 1671980593087 ThreadName = ThreadB
	 * begin awaitA 时间为 1671980593088 ThreadName = ThreadA
	 * signalAll 时间为 1671980596087 ThreadName = main
	 * end awaitB 时间为 1671980596087 ThreadName = ThreadB
	 * end awaitA 时间为 1671980596087 ThreadName = ThreadA
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter04_A3 chapter04_a3 = new Chapter04_A3();
		Chapter04_A3_ThreadA chapter04_a3_threadA = new Chapter04_A3_ThreadA(chapter04_a3);
		Chapter04_A3_ThreadB chapter04_a3_threadB = new Chapter04_A3_ThreadB(chapter04_a3);
		chapter04_a3_threadA.setName("ThreadA");
		chapter04_a3_threadB.setName("ThreadB");
		
		chapter04_a3_threadA.start();
		chapter04_a3_threadB.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		chapter04_a3.signalAll();
	}
}