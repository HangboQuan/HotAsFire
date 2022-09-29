package chapter01;

/**
 * @author quanhangbo
 * @date 22-9-29 下午7:46
 */
public class Chapter01_B1 extends Thread{

	public static void main(String[] args) {

		try{
			Chapter01_B1 chapter01_b1 = new Chapter01_B1();
			chapter01_b1.start();
			Thread.sleep(1000);
			chapter01_b1.interrupt();
			System.out.println("是否停止 = A?:" + chapter01_b1.isInterrupted());
			System.out.println("是否停止 = B?:" + chapter01_b1.isInterrupted());
		}catch (Exception e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("end");
	}
}
