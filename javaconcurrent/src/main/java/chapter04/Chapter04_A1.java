package chapter04;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author quanhangbo
 * @date 2022/12/19 20:23
 */
public class Chapter04_A1 {
	private Lock lock = new ReentrantLock();
	
	public void methodA() {
		lock.lock();
		try {
			System.out.println("methodA begin ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodA end ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void methodB() {
		lock.lock();
		try {
			System.out.println("methodB begin ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println("methodB end ThreadName = " + Thread.currentThread().getName() + " time = " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

class Chapter04_A0_ThreadA extends Thread {
	private Chapter04_A1 chapter04_a1;
	
	public Chapter04_A0_ThreadA(Chapter04_A1 chapter04_a1) {
		this.chapter04_a1 = chapter04_a1;
	}
	
	@Override
	public void run() {
		chapter04_a1.methodA();
	}
}


class Chapter04_A0_ThreadAA extends Thread {
	private Chapter04_A1 chapter04_a1;
	
	public Chapter04_A0_ThreadAA(Chapter04_A1 chapter04_a1) {
		this.chapter04_a1 = chapter04_a1;
	}
	
	@Override
	public void run() {
		chapter04_a1.methodA();
	}
}

class Chapter04_A0_ThreadB extends Thread {
	private Chapter04_A1 chapter04_a1;
	
	public Chapter04_A0_ThreadB(Chapter04_A1 chapter04_a1) {
		this.chapter04_a1 = chapter04_a1;
	}
	
	@Override
	public void run() {
		chapter04_a1.methodB();
	}
}


class Chapter04_A0_ThreadBB extends Thread {
	private Chapter04_A1 chapter04_a1;
	
	public Chapter04_A0_ThreadBB(Chapter04_A1 chapter04_a1) {
		this.chapter04_a1 = chapter04_a1;
	}
	
	@Override
	public void run() {
		chapter04_a1.methodB();
	}
}

class Chapter04_A1_01 {

	/**
	 * lock.lock()代码的线程 持有了对象监视器，其他线程只能等待锁被释放再次争抢  效果和synchronized关键字一样
	 * methodA begin ThreadName = a time = 1671453464413
	 * methodA end ThreadName = a time = 1671453469413
	 * methodA begin ThreadName = AA time = 1671453469413
	 * methodA end ThreadName = AA time = 1671453474414
	 * methodB begin ThreadName = b time = 1671453474414
	 * methodB end ThreadName = b time = 1671453479414
	 * methodB begin ThreadName = BB time = 1671453479414
	 * methodB end ThreadName = BB time = 1671453484415
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Chapter04_A1 chapter04_a1 = new Chapter04_A1();
			Chapter04_A0_ThreadA chapter04_a0_threadA = new Chapter04_A0_ThreadA(chapter04_a1);
			chapter04_a0_threadA.setName("a");
			chapter04_a0_threadA.start();
			
			Chapter04_A0_ThreadAA chapter04_a0_threadAA = new Chapter04_A0_ThreadAA(chapter04_a1);
			chapter04_a0_threadAA.setName("AA");
			chapter04_a0_threadAA.start();
			
			Thread.sleep(100);
			
			Chapter04_A0_ThreadB chapter04_a0_threadB = new Chapter04_A0_ThreadB(chapter04_a1);
			chapter04_a0_threadB.setName("b");
			chapter04_a0_threadB.start();
			
			Chapter04_A0_ThreadBB chapter04_a0_threadBB = new Chapter04_A0_ThreadBB(chapter04_a1);
			chapter04_a0_threadBB.setName("BB");
			chapter04_a0_threadBB.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}