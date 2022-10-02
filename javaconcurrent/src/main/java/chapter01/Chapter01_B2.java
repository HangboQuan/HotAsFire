package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/2 3:02
 */
public class Chapter01_B2 extends Thread{

	@Override
	public void run() {
		
		for(int i = 0; i < 500000; i ++ ) {
			// 这两个方法都能中断线程，区别是什么？在什么情况下使用哪个方法？
			System.out.println("i = " + i);
			if(this.isInterrupted()) {
				System.out.println("end");
				break;
			}
			
		}
	}
}

class Chapter01_B2_01 {
	
	public static void main(String[] args){
		
		try {
			Chapter01_B2 chapter01_b2 = new Chapter01_B2();
			chapter01_b2.start();
			
			Thread.sleep(1000);
			// interrupt()并不会立即中断线程,而是设置一个中断标志，需要和interrupted()或者isInterrupted()联用
			// interrupted()中断的是当前线程, 而isInterrupted()中断的是线程实例对象的线程(哪个对象调用的start())
			// interrupt()是给线程设置中断标志
			// interrupted()是检测中断状态并清楚中断状态
			// isInterrupted()只检测中断
			chapter01_b2.interrupt();
			System.out.println("thread is interrupted = ?:" + chapter01_b2.isInterrupted());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
