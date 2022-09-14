package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/14 21:05
 */
public class Chapter01_A1 extends Thread {
	
	@Override
	public void run(){
		try{
			for(int i = 0; i < 10; i ++ ){
				int time = (int)(Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("run=" + Thread.currentThread().getName());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * result: 该结果是随机的
	 * main=main
	 * run=chapter01_a1
	 * main=main
	 * run=chapter01_a1
	 * main=main
	 * main=main
	 * run=chapter01_a1
	 * run=chapter01_a1
	 * main=main
	 * run=chapter01_a1
	 * main=main
	 * run=chapter01_a1
	 * run=chapter01_a1
	 * run=chapter01_a1
	 * main=main
	 * run=chapter01_a1
	 * run=chapter01_a1
	 * main=main
	 * main=main
	 * main=main
	 * @param args
	 */
	public static void main(String[] args){
		Chapter01_A1 chapter01_a1 = new Chapter01_A1();
		chapter01_a1.setName("chapter01_a1");

		/**
		 * start()方法是通知"线程规划器"此线程已经准备就绪，等待调用线程对象的run()方法
		 * 这个过程就是让系统安排一个时间来调用Thread.run(),使线程得到运行
		 *
		 * 如果直接调用thread.run()就不是异步执行，由主线程来处理
		 */
		chapter01_a1.start();
		
		try{
			for(int i = 0; i < 10; i ++ ){
				int time = (int) (Math.random() * 1000);
				Thread.sleep(time);
				System.out.println("main=" + Thread.currentThread().getName());
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
