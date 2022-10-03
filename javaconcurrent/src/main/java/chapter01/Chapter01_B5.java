package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 10:38
 */
public class Chapter01_B5 extends Thread{
	private int i = 0;
	
	@Override
	public void run(){
		
		try{
			while(true){
				i ++;
				System.out.println("i = " + i);
				Thread.sleep(1000);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args){
		try{
			Chapter01_B5 chapter01_b5 = new Chapter01_B5();
			chapter01_b5.start();
			
			Thread.sleep(5000);
			// stop()强制停止线程，为什么要废弃这个方法？有什么缺点吗？
			chapter01_b5.stop();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter01_B5_01 extends Thread{
	
	@Override
	public void run() {
		try{
			/**
			 * java.lang.ThreadDeath
			 * 	at java.base/java.lang.Thread.stop(Thread.java:943)
			 *
			 * 	stop()已经作废，如果让线程停止则有可能使一些清理性的工作得不到完成
			 * 	对锁定的对象进行解锁，导致数据得不到同步的处理，出现数据不一致的问题
			 */
			this.stop();
		}catch (ThreadDeath e) {
			System.out.println("Thread Death");
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		Chapter01_B5_01 chapter01_b5_01 = new Chapter01_B5_01();
		chapter01_b5_01.start();
	}
}

class Chapter01_B5_02 {
	private String username = "a";
	private String password = "aa";
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername() {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		this.password = password;
	}
	
	public void printString(String username, String password) {
		try{
			this.username = username;
			Thread.sleep(100000);
			this.password = password;
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
}

class Chapter01_B5_03 extends Thread{
	private Chapter01_B5_02 chapter01_b5_02;
	
	public Chapter01_B5_03(Chapter01_B5_02 chapter01_b5_02){
		super();
		this.chapter01_b5_02 = chapter01_b5_02;
		System.out.println(this.chapter01_b5_02.getUsername());
		System.out.println(this.chapter01_b5_02.getPassword());
	}
	
	@Override
	public void run() {
		chapter01_b5_02.printString("b", "bb");
	}
	
	public static void main(String[] args){
		try{
			Chapter01_B5_02 chapter01_b5_02 = new Chapter01_B5_02();
			Chapter01_B5_03 chapter01_b5_03 = new Chapter01_B5_03(chapter01_b5_02);
			
			chapter01_b5_03.start();
			
			/**
			 * result:
			 * a
			 * aa
			 * b aa
			 *
			 * explain:在初始化对象的时候，调用构造方法，username = a, password = aa
			 * 然后另一个线程执行run()时，覆盖了username = b, 线程休息100s，在线程休息的时候stop了线程
			 * 因此username = b, password = aa(使用的是原值，未覆盖)
			 *
			 * 如果run()的休眠时间为10ms的时候，那么最终的结果是b bb
			 */
			Thread.sleep(1000);
			chapter01_b5_03.stop();
			System.out.println(chapter01_b5_02.getUsername() + " " + chapter01_b5_02.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
