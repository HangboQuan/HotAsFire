package chapter03;

import java.util.concurrent.ExecutionException;

/**
 * @author quanhangbo
 * @date 22-11-6 下午6:43
 */
public class Chapter03_A1 {

	/**
	 * wait()/notify()/notifyAll() 都是超类Object的方法，调用这3个方法之前 都必须首先获得该对象的对象级别的锁
	 * 如果没有持有适当的锁，则会跑出IllegalMonitorStateException
	 *
	 * wait(): 使当前代码的线程进行等待，并且会释放锁
	 * notify(): 通知可能等待该对象的对象锁的线程，如果有多个线程等待，则随机通知一个线程; 执行notify()并不会马上释放对象锁，wait()并不会
	 * 马上获取锁，要等到notify执行完(退出同步代码块)，当前线程才会释放锁 -> wait()才能获取对象锁
	 *
	 * 调用wait()必须使用notify()/notifyAll()来通知，如果不通知，则该同步代码块会一直阻塞
	 *
	 *
	 */

	public static void main(String[] args) {

		/**
		 * 抛出异常
		 * java.lang.IllegalMonitorStateException
		 * 	at java.base/java.lang.Object.wait(Native Method)
		 * 	at java.base/java.lang.Object.wait(Object.java:326)
		 * 	at chapter03.Chapter03_A1.main(Chapter03_A1.java:13)
		 */
		 try {
		 	String newString = new String("");
		 	newString.wait();
//		 	newString.notify();
//		 	newString.notifyAll();
		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
	}
}

class Chapter03_A1_01 {

	public static void main(String[] args) {

		/**
		 * 线程就这样一直阻塞着，不会继续向下执行
		 * result:
		 * syn 上面
		 * syn 第一行
		 */
		try {
			String lock = new String();

			System.out.println("syn 上面");
			synchronized (lock) {
				System.out.println("syn 第一行");
				lock.wait();
				System.out.println("wait 下的代码！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
