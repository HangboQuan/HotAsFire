package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 15:20
 */
public class Chapter01_B8 {
	
	public synchronized void printString() {
		System.out.println("begin");
		if(Thread.currentThread().getName().equalsIgnoreCase("a")){
			System.out.println("a 线程永远 suspend了! ");
			Thread.currentThread().suspend();
		}
		System.out.println("end");
	}
	
	public static void main(String[] args){
		
		/**
		 * result:
		 * begin
		 * a 线程永远 suspend了!
		 * thread1 启动，但是进不了printString()方法
		 * printString()方法被a线程锁定并且永远suspend 暂停了！
		 *
		 * 原因：由于加锁synchronized, 执行suspend暂停线程，该对象被独占且得不到释放
		 * 造成公共的同步对象的独占，使得其他对象无法访问公共同步对象
		 */
		try{
			Chapter01_B8 chapter01_b8 = new Chapter01_B8();
			Thread thread = new Thread() {
				@Override
				public void run(){
					chapter01_b8.printString();
				}
			};
			thread.setName("a");
			thread.start();
			Thread.sleep(1000);
			
			Thread thread1 = new Thread() {
				
				@Override
				public void run() {
					System.out.println("thread1 启动，但是进不了printString()方法");
					System.out.println("printString()方法被a线程锁定并且永远suspend 暂停了！");
					chapter01_b8.printString();
				}
			};
			
			thread1.setName("b");
			thread1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Chapter01_B8_01 extends Thread{
	private long i = 0;
	
	@Override
	public void run(){
		while(true){
			i ++;
			/**
			 * 增加这行代码的时候，就无法打印main end
			 * 同理：println()中有synchronized锁，同步锁锁上之后，调用suspend暂停了线程，同步锁未得到释放
			 * 则无法打印其他情况下的println()
			 * public void println(long x) {
			 *         synchronized (this) {
			 *             print(x);
			 *             newLine();
			 *         }
			 *     }
			 */
			System.out.println(i);
		}
	}
	
	public static void main(String[] args){
		
		try{
			Chapter01_B8_01 chapter01_b8_01 = new Chapter01_B8_01();
			chapter01_b8_01.start();
			Thread.sleep(1000);
			chapter01_b8_01.suspend();
			System.out.println("main end");
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
}

class Chapter01_B8_02 {
	
	private String username = "1";
	private String password = "11";

	/**
	 * 注意：线程数据同步问题
	 * 停止a线程！
	 * username = a, password = 11
	 * @param username
	 * @param password
	 */
	public void setUsername(String username, String password){
		this.username = username;
		if(Thread.currentThread().getName().equalsIgnoreCase("a")){
			System.out.println("停止a线程！");
			Thread.currentThread().suspend();
		}
		this.password = password;
	}
	
	public void printString(){
		System.out.println("username = " + username + ", password = " + password);
	}
	
	public static void main(String[] args){
		
		try{
			Chapter01_B8_02 chapter01_b8_02 = new Chapter01_B8_02();
			Thread thread = new Thread() {
				@Override
				public void run() {
					chapter01_b8_02.setUsername("a", "aa");
				}
			};
			thread.setName("a");
			thread.start();
			Thread.sleep(1000);
			
			Thread thread1 = new Thread(){
				
				@Override
				public void run(){
					chapter01_b8_02.printString();
				}
			};
			
			thread1.setName("b");
			thread1.start();
		}catch (Exception e){
			e.printStackTrace();
		}
		
		
	}
}
