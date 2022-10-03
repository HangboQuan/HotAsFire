package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 12:47
 */
public class Chapter01_B6 extends Thread{
	
	@Override
	public void run() {
	
		while(true) {
			if(this.isInterrupted()) {
				System.out.println("this thread has interrupted!");
				return ;
			}
		}
	
	}
	
	public static void main(String[] args) {
		try{
			/**
			 * 建议使用"抛异常"的方法来实现线程的停止，因为在catch块中还可以将异常向上抛，
			 * 使线程停止的事件得以传播
			 */
			Chapter01_B6 chapter01_b6 = new Chapter01_B6();
			chapter01_b6.start();
			Thread.sleep(1000);
			chapter01_b6.interrupt();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
