package chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/26 23:03
 */
public class Chapter04_A4 {
	
	private Lock lock = new ReentrantLock();
	public Condition conditionA = lock.newCondition();
	public Condition conditionB = lock.newCondition();
	
	public void awaitA() {
		lock.lock();
		try {
			System.out.println("begin awaitA 时间为 " + System.currentTimeMillis() + " ThreadName = "
				+ Thread.currentThread().getName());
			conditionA.await();
			System.out.println("end awaitA 时间为 " + System.currentTimeMillis() + " ThreadName = "
				+ Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void awaitB() {
		lock.lock();
		try {
			System.out.println("begin awaitB 时间为 " + System.currentTimeMillis() + " ThreadName = "
					                   + Thread.currentThread().getName());
			conditionB.await();
			System.out.println("end awaitB 时间为 " + System.currentTimeMillis() + " ThreadName = "
					                   + Thread.currentThread().getName());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAll_A() {
		lock.lock();
		try {
			System.out.println("signAll_A 时间为 " + System.currentTimeMillis()
				+ " ThreadName = " + Thread.currentThread().getName());
			conditionA.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signalAll_B() {
		lock.lock();
		try {
			System.out.println("signAll_B 时间为 " + System.currentTimeMillis()
					                   + " ThreadName = " + Thread.currentThread().getName());
			conditionB.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A4_ThreadA extends Thread {
	private Chapter04_A4 chapter04_a4;
	
	public Chapter04_A4_ThreadA(Chapter04_A4 chapter04_a4) {
		this.chapter04_a4 = chapter04_a4;
	}
	
	@Override
	public void run() {
		chapter04_a4.awaitA();
	}
}

class Chapter04_A4_ThreadB extends Thread {
	private Chapter04_A4 chapter04_a4;
	
	public Chapter04_A4_ThreadB(Chapter04_A4 chapter04_a4) {
		this.chapter04_a4 = chapter04_a4;
	}
	
	@Override
	public void run() {
		chapter04_a4.awaitB();
	}
}

class Chapter04_A4_01 {

	public static void main(String[] args) throws Exception {
		Chapter04_A4 chapter04_a4 = new Chapter04_A4();
		Chapter04_A4_ThreadA chapter04_a4_threadA = new Chapter04_A4_ThreadA(chapter04_a4);
		Chapter04_A4_ThreadB chapter04_a4_threadB = new Chapter04_A4_ThreadB(chapter04_a4);
		
		chapter04_a4_threadA.setName("ThreadA");
		chapter04_a4_threadB.setName("ThreadB");
		
		chapter04_a4_threadA.start();
		chapter04_a4_threadB.start();
		
		Thread.sleep(3000);
		
		/**
		 * 只唤醒了线程A
		 * begin awaitB 时间为 1672067668089 ThreadName = ThreadB
		 * begin awaitA 时间为 1672067668090 ThreadName = ThreadA
		 * signAll_A 时间为 1672067671090 ThreadName = main
		 * end awaitA 时间为 1672067671090 ThreadName = ThreadA
		 */
		chapter04_a4.signalAll_A();
	}
	
}