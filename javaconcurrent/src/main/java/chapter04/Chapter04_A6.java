package chapter04;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/29 23:42
 */
public class Chapter04_A6 {
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	private boolean border = false;
	public void methodA() {
		lock.lock();
		try {
			while(border == true) {
				condition.await();
				System.out.println("可能连续打印AA");
			}
			System.out.println("A");
			border = true;
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void methodB() {
		lock.lock();
		try {
			while(border == false) {
				condition.await();
				System.out.println("可能连续打印BB");
			}
			System.out.println("B");
			border = false;
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A6_01 extends Thread {
	private Chapter04_A6 chapter04_a6;
	
	public Chapter04_A6_01(Chapter04_A6 chapter04_a6) {
		this.chapter04_a6 = chapter04_a6;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i ++ ) {
			chapter04_a6.methodA();
		}
	}
}


class Chapter04_A6_02 extends Thread {
	private Chapter04_A6 chapter04_a6;
	
	public Chapter04_A6_02(Chapter04_A6 chapter04_a6) {
		this.chapter04_a6 = chapter04_a6;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i ++ ) {
			chapter04_a6.methodB();
		}
	}
}

class Chapter04_A6_03 {

	public static void main(String[] args) {
		Chapter04_A6 chapter04_a6 = new Chapter04_A6();
		Chapter04_A6_01[] chapter04_a6_01s = new Chapter04_A6_01[10];
		Chapter04_A6_02[] chapter04_a6_02s = new Chapter04_A6_02[10];
		
		// 如果是signal的话，会出现假死(线程都处于等待中了)
		for(int i = 0; i < 10; i ++ ) {
			chapter04_a6_01s[i] = new Chapter04_A6_01(chapter04_a6);
			chapter04_a6_02s[i] = new Chapter04_A6_02(chapter04_a6);
			chapter04_a6_01s[i].start();
			chapter04_a6_02s[i].start();
		}
	}
}
