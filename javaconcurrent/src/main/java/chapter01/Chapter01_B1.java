package chapter01;

/**
 * @author quanhangbo
 * @date 22-9-29 下午7:46
 */
public class Chapter01_B1 extends Thread{
	
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 500000; i ++ ) {
			if (i % 100 == 0) {
				System.out.println("i = " + i);
			}
		}
	}
}

class Chapter01_B1_01 {
	public static void main(String[] args) {
		
		try{
			
			Chapter01_B1 chapter01_b1 = new Chapter01_B1();
			chapter01_b1.start();
			Thread.sleep(1000);
			chapter01_b1.interrupt();
			
			/**
			 * 很奇怪, 书上说的是
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
