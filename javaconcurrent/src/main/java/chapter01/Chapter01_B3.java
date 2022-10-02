package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/2 20:47
 */
public class Chapter01_B3  extends Thread {
	
	@Override
	public void run() {
		super.run();
		for(int i = 0; i < 500000; i ++ ) {
			System.out.println("i = " + i);
			// 虽然这里中断了线程，但是这里只是跳出了for循环，for循环后面的输出语句还是被打印出来了
			if(this.isInterrupted()) {
				System.out.println("this thread has been interrupted()");
				break;
			}
		}
		System.out.println("Is this thread interrupted?");
//		if(!this.isInterrupted()) {
//			System.out.println("Is this thread interrupted?");
//		}
	}
	
	public static void main(String[] args) {
		try{
			Chapter01_B3 chapter01_b3 = new Chapter01_B3();
			chapter01_b3.start();
			Thread.sleep(1000);
			chapter01_b3.interrupt();
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}

class Chapter01_B3_01 extends Thread{

	/**
	 * 通过抛异常来 终止线程
	 */
	@Override
	public void run() {
		try{
			for(int i = 0; i < 500000; i ++) {
				System.out.println("i = " + i);
				if(this.isInterrupted()) {
					System.out.println("interrupt this thread");
					throw new InterruptedException();
				}
			}
			System.out.println("is this thread interrupted?");
		}catch (Exception e) {
			System.out.println("this thread get into exception");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try{
			Chapter01_B3_01 chapter01_b3_01 = new Chapter01_B3_01();
			chapter01_b3_01.start();
			Thread.sleep(1000);
			chapter01_b3_01.interrupt();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
