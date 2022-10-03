package chapter01;

/**
 * @author quanhangbo
 * @date 2022/10/3 17:50
 */
public class Chapter01_B9 extends Thread {
	
	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int count = 0;
		for(int i = 0; i < 1000000; i ++ ){
			/**
			 * 注释掉Thread.yield(): 耗时： 4
			 * 放开Thread.yield(): 耗时： 111
			 * yield(): 放弃当前的CPU资源，让其他任务去抢占CPU时间片，放弃CPU的时间不确定，有可能刚刚放弃，又
			 * 马上获得CPU
			 */
			Thread.yield();
			count += i;
		}
		long end = System.currentTimeMillis();
		System.out.println("耗时：" + (end - start));
	}
	
	public static void main(String[] args){
		Chapter01_B9 chapter01_b9 = new Chapter01_B9();
		chapter01_b9.start();
	}
}
