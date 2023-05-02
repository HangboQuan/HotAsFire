package chapter05;

import java.util.concurrent.CyclicBarrier;

/**
 * @author quanhangbo
 * @date 2023/5/2 22:58
 */
public class Chapter05_A1 {
	static CyclicBarrier c = new CyclicBarrier(3);
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (Exception e) {
				
				}
				System.out.println(1);
			}
		}).start();
		
		try {
			c.await();
		} catch (Exception e) {
		
		}
		System.out.println(2);
	}
}
