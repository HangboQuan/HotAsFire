package chapter04;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/14 20:45
 */
public class Chapter04_A0 {
	/**
	 * synchronized 关键字来实现线程之间的同步互斥，在jdk1.5后新增的ReentrantLock也能达到同样的效果
	 * 在扩展功能上 也更强大，比如：嗅探锁定、多路分支通知等
	 * 使用上比synchronized更加灵活
	 */
	private Lock lock = new ReentrantLock();

	/**
	 * 当前线程打印完毕后将锁释放，其他线程才可以继续打印
	 */
	public void testMethod() {
		lock.lock();
		for (int i = 0; i < 5; i ++ ) {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + (" " + (i + 1)));
		}
		lock.unlock();
	}
}

class Chapter04_A0_01 extends Thread {
	private Chapter04_A0 chapter04_a0;
	
	public Chapter04_A0_01(Chapter04_A0 chapter04_a0) {
		this.chapter04_a0 = chapter04_a0;
	}
	
	@Override
	public void run() {
		chapter04_a0.testMethod();
	}

}

class Chapter04_A0_02 {
	public static void main(String[] args) {
		Chapter04_A0 chapter04_a0 = new Chapter04_A0();
		Chapter04_A0_01 a1 = new Chapter04_A0_01(chapter04_a0);
		Chapter04_A0_01 a2 = new Chapter04_A0_01(chapter04_a0);
		Chapter04_A0_01 a3 = new Chapter04_A0_01(chapter04_a0);
		Chapter04_A0_01 a4 = new Chapter04_A0_01(chapter04_a0);
		Chapter04_A0_01 a5 = new Chapter04_A0_01(chapter04_a0);
		
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
	}
}