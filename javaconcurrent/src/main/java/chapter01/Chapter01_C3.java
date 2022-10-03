package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 20:36
 */
public class Chapter01_C3 extends Thread {

	/**
	 * Java 线程中有两种线程，一种是用户线程 另一种是守护线程
	 *
	 * 守护线程是一种特殊的线程，它的特性有'陪伴'的含义，当进程中不存在非守护线程，则守护线程自动销毁
	 * 典型的守护线程是垃圾回收线程 当进程中没有非守护线程了，则垃圾回收线程也没有存在的必要了，自动销毁
	 *
	 * 通俗的解释：守护线程：任何一个守护线程是整个JVM中所有非守护线程的'保姆'，只要当前JVM实例存在
	 * 任何一个非守护线程没有结束，守护线程就不结束 当最后一个非守护线程结束是，守护线程才随着JVM一同结束
	 * 工作
	 *
	 * Daemon的作用是为其他线程的运行提供便利服务，守护线程最典型的应用时GC
	 */
	
	private int i = 0;
	@Override
	public void run() {
		try{
			while(true) {
				i ++;
				System.out.println("i = " + i);
				Thread.sleep(1000);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		try{
			Chapter01_C3 chapter01_c3 = new Chapter01_C3();
			chapter01_c3.setDaemon(true);
			chapter01_c3.start();
			Thread.sleep(5000);
			
			System.out.println("离开chapter01_c3 对象也不再打印了，也就是停止了！");
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
}
