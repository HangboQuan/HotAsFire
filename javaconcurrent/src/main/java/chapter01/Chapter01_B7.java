package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 12:53
 */
public class Chapter01_B7 extends Thread {
	
	private long i = 0;
	public long getI(){
		return i;
	}
	
	public void setI(long i){
		this.i = i;
	}
	
	@Override
	public void run() {
		while(true){
			i ++;
		}
	}
	
	
	public static void main(String[] args) {
		
		try{
			
			/**
			 * result: suspend()方法暂停线程 resume()方法恢复线程的执行
			 * A = 1664775043481 i = 2699622552
			 * A = 1664775048481 i = 2699622552
			 * B = 1664775053482 i = 5430114385
			 * B = 1664775058482 i = 5430114385
			 */
			Chapter01_B7 chapter01_b7 = new Chapter01_B7();
			chapter01_b7.start();
			Thread.sleep(5000);
			
			chapter01_b7.suspend();
			System.out.println("A = " + System.currentTimeMillis() + " i = " + chapter01_b7.getI());
			
			Thread.sleep(5000);
			System.out.println("A = " + System.currentTimeMillis() + " i = " + chapter01_b7.getI());
			
			chapter01_b7.resume();
			Thread.sleep(5000);
			
			chapter01_b7.suspend();
			System.out.println("B = " + System.currentTimeMillis() + " i = " + chapter01_b7.getI());
			Thread.sleep(5000);
			
			System.out.println("B = " + System.currentTimeMillis() + " i = " + chapter01_b7.getI());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
