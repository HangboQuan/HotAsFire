package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/2 21:49
 */
public class Chapter01_B4 extends Thread {
	
	@Override
	public void run() {
		try{
			super.run();
			System.out.println("run begin ");
			Thread.sleep(100000);
			System.out.println("run end ");
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		try{
			Chapter01_B4 chapter01_b4 = new Chapter01_B4();
			chapter01_b4.start();
			
			/**
			 * java.lang.InterruptedException: sleep interrupted
			 * 	at java.base/java.lang.Thread.sleep(Native Method)
			 */
			Thread.sleep(1000);
			chapter01_b4.interrupt();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
