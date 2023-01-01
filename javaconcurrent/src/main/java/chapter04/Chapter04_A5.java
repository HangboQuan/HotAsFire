package chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/28 22:44
 */
public class Chapter04_A5 {
	
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;
	
	public void set() {
		lock.lock();
		try {
			while(hasValue == true) {
				condition.await();
			}
			System.out.print("A ");
			hasValue = true;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	public void get() {
		lock.lock();
		try {
			while(hasValue == false) {
				condition.await();
			}
			System.out.print("B ");
			hasValue = false;
			condition.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A5_01 extends Thread {
	private Chapter04_A5 chapter04_a5;
	
	public Chapter04_A5_01(Chapter04_A5 chapter04_a5) {
		this.chapter04_a5 = chapter04_a5;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i ++ ) {
			chapter04_a5.set();
		}
	}
}


class Chapter04_A5_02 extends Thread {
	private Chapter04_A5 chapter04_a5;
	
	public Chapter04_A5_02(Chapter04_A5 chapter04_a5) {
		this.chapter04_a5 = chapter04_a5;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i ++ ) {
			chapter04_a5.get();
		}
	}
}

class Chapter04_A5_03 {

	/**
	 * 实现交替打印A、B
	 * A B A B A B A B A B A B A B A B A B A B
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter04_A5 chapter04_a5 = new Chapter04_A5();
		Chapter04_A5_01 chapter04_a5_01 = new Chapter04_A5_01(chapter04_a5);
		chapter04_a5_01.start();
		
		Chapter04_A5_02 chapter04_a5_02 = new Chapter04_A5_02(chapter04_a5);
		chapter04_a5_02.start();
	}
}
