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

	/**
	 * join()使所属线程对象x正常执行run()的任务 从而使当前线程阻塞 等待x线程销毁后在继续执行当前线程的代码
	 * join()具有使线程排队运行的作用 类似同步的运行效果， join()和synchronized的区别是：join()使用wait()进行等待，synchronize使用对象监视器
	 * 原理作为同步
	 * @param args
	 */
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
