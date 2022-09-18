package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/18 19:38
 */
public class Chapter01_A8 extends Thread{
	
	// isAlive()的功能是判断当前线程是否处于活动状态
	// 活动状态是线程已经启动且尚未终止，线程处于正在运行或准备开始的状态，就认为线程是存活的
	@Override
	public void run() {
		System.out.println("run=" + this.isAlive());
	}


	/**
	 * result:
	 * begin == false
	 * end == true
	 * run=true
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter01_A8 chapter01_a8 = new Chapter01_A8();
		System.out.println("begin == " + chapter01_a8.isAlive());
		chapter01_a8.start();
		try{
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/**
		 * 加上休眠时间的时间的话，线程已经运行结束了
		 * result:
		 * begin == false
		 * run=true
		 * end == false
		 */
		System.out.println("end == " + chapter01_a8.isAlive());
	}
}
