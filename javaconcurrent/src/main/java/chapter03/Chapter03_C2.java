package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/24 22:18
 */
public class Chapter03_C2 extends Thread {
	// join() 作用是等待线程对象销毁
	
	@Override
	public void run() {
		try {
			int secondValue = (int) (Math.random() * 10000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Chapter03_C2_01 {

	/**
	 * 我想要执行在main执行完之前，执行run
	 * 8959
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_C2 chapter03_c2 = new Chapter03_C2();
		chapter03_c2.start();
		// 这里并不知道子线程休息了多长时间? Thread.sleep(?)
		System.out.println("我想要执行在main执行完之前，执行run");
	}
}

class Chapter03_C2_02 {
	
	public static void main(String[] args) {
		
		/**
		 * 6764
		 * 等子线程运行完，main线程结束
		 */
		try {
			Chapter03_C2 chapter03_c2 = new Chapter03_C2();
			chapter03_c2.start();
			
			chapter03_c2.join();
			System.out.println("等子线程运行完，main线程结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
