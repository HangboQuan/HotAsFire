package chapter01;

/**
 * @author quanhangbo
 * @date 22-9-29 下午7:46
 */
public class Chapter01_B1 extends Thread{
	
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 5000000; i ++ ) {
			
			System.out.println("i = " + i);
			
		}
	}
}

class Chapter01_B1_01 {
	public static void main(String[] args) {
		
		try{
			
			Chapter01_B1 chapter01_b1 = new Chapter01_B1();
			chapter01_b1.start();
			Thread.sleep(3000);
			chapter01_b1.interrupt();
			
			/**
			 * 很奇怪, 中断了线程，那么isInterrupted理论上本应该是true的，但是代码实际运行过程中却是false?
			 * 是否停止 = A?:false
			 * 是否停止 = B?:false
			 */
			System.out.println("是否停止 = A?:" + chapter01_b1.isInterrupted());
			System.out.println("是否停止 = B?:" + chapter01_b1.isInterrupted());
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
