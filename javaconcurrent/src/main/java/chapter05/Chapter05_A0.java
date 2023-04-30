package chapter05;

import java.util.concurrent.CountDownLatch;

/**
 * @author quanhangbo
 * @date 2023/4/30 23:14
 */
public class Chapter05_A0 {

	
	/**
	 * CountDownLatch允许一个或多个线程等待其他线程完成操作， 可以代替join
	 */
	static CountDownLatch c = new CountDownLatch(2);

	public static void main(String[] args) throws Exception {
	//		Thread parser1 = new Thread(new Runnable() {
	//			@Override
	//			public void run() {
	//
	//			}
	//		});
	//		Thread parser2 = new Thread(new Runnable() {
	//			@Override
	//			public void run() {
	//
	//			}
	//		});
	//
	//		parser1.start();
	//		parser2.start();
	//		parser1.join();
	//		parser2.join();
	//		System.out.println("all parser finish!");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(1);
				c.countDown();
				System.out.println(2);
				c.countDown();
			}
		}).start();
		c.await();
		System.out.println(3);
		
		
		
	}
	
}
