package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 20:19
 */
public class Chapter01_C2 extends Thread {
	
	private int count = 0;
	
	public int getCount() {
		return count;
	}
	
	@Override
	public void run() {
		while(true) {
			count ++;
		}
	}
}

class Chapter01_C2_01 extends Thread{
	private int count = 0;
	
	public int getCount() {
		return count;
	}
	
	@Override
	public void run() {
		while(true) {
			count ++;
		}
	}

	public static void main(String[] args) {
		try{
			Chapter01_C2 chapter01_c1 = new Chapter01_C2();
			chapter01_c1.setName("ThreadA");
			chapter01_c1.setPriority(Thread.NORM_PRIORITY - 3);
			chapter01_c1.start();
			
			Chapter01_C2_01 chapter01_c2 = new Chapter01_C2_01();
			chapter01_c2.setName("ThreadB");
			chapter01_c2.setPriority(Thread.NORM_PRIORITY + 3);
			chapter01_c2.start();
			
			Thread.sleep(1000);
			
			chapter01_c1.stop();
			chapter01_c2.stop();
			
			
			/**
			 * result:
			 * a = 484415751
			 * b = 509812185
			 */
			System.out.println("a = " + chapter01_c1.getCount());
			System.out.println("b = " + chapter01_c2.getCount());
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
