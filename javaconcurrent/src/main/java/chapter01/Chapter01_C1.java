package chapter01;

import java.util.Random;

/**
 * @author quanhangbo
 * @date 2022/10/3 19:48
 */
public class Chapter01_C1 extends Thread {
	
	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		for(int i = 0; i < 1000; i ++ ){
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("************** thread 1 use time = " + (endTime - beginTime));
	}
}

class Chapter01_C1_01 extends Thread{
	
	@Override
	public void run(){
		long beginTime = System.currentTimeMillis();
		for(int i = 0; i < 1000; i ++ ){
			Random random = new Random();
			random.nextInt();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("&&&&&&&&&&&&&&&&& thread 2 use time = " + (endTime - beginTime));
	}
	
	
	public static void main(String[] args){
		
		/**
		 * 优先级较高的线程并不一定每一次都先执行完run()方法中的任务
		 *
		 * 线程的优先级与打印顺序无关
		 */
		for(int i = 0; i < 5; i ++ ){
			Chapter01_C1_01 chapter01_c1_01 = new Chapter01_C1_01();
			chapter01_c1_01.setPriority(5);
			chapter01_c1_01.start();
			
			Chapter01_C1 chapter01_c1 = new Chapter01_C1();
			chapter01_c1.setPriority(6);
			chapter01_c1.start();
		}
	}
}
