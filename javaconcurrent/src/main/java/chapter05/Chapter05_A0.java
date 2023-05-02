package chapter05;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author quanhangbo
 * @date 2023/4/30 23:14
 */
public class Chapter05_A0 {

	
	/**
	 * CountDownLatch允许一个或多个线程等待其他线程完成操作， 可以代替join
	 * 计数器必须>=0
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
		long start = System.currentTimeMillis();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println(1);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					c.countDown();
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println(2);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					c.countDown();
				}
				
			}
		}).start();
		c.await(2000, TimeUnit.MILLISECONDS);
		System.out.println(3);
		System.out.println(System.currentTimeMillis() - start);
	}
	
}
