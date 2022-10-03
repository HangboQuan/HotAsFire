package chapter01;

import java.util.Random;

/**
 * @author quanhangbo
 * @date 2022/10/3 18:00
 */
public class Chapter01_C0 extends Thread{
	
	@Override
	public void run(){
		System.out.println("Chapter01_C0 run priority = " + this.getPriority());
		Chapter01_C0_01 chapter01_c0_01 = new Chapter01_C0_01();
		chapter01_c0_01.start();
	}

	public static void main(String[] args) {
		/**
		 * 线程优先级
		 * 1 5 10
		 *
		 * 线程的优先级 具有继承性|传递性
		 */
	}
}

class Chapter01_C0_01 extends Thread{
	
	@Override
	public void run(){
		System.out.println("Chapter01_C0_01 run priority = " + this.getPriority());
	}

	public static void main(String[] args) {
		
		/**
		 * main thread priority = 5
		 * main thread end  priority = 10
		 * Chapter01_C0_01 run priority = 10
		 */
		System.out.println("main thread priority = " + Thread.currentThread().getPriority());
		Thread.currentThread().setPriority(10);
		System.out.println("main thread end  priority = " + Thread.currentThread().getPriority());
		
		Chapter01_C0_01 chapter01_c0_01 = new Chapter01_C0_01();
		chapter01_c0_01.start();
	}
}

class Chapter01_C0_02 extends Thread{
	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		long addResult = 0;
		
		for(int i = 0; i < 10; i ++ ){
			for(int j = 0; j < 500000; j ++ ){
				Random random = new Random();
				random.nextInt();
				addResult = addResult + i;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("********** thread1 use time = " + (endTime - beginTime));
	}
	
}

class Chapter01_C0_03 extends Thread{
	
	@Override
	public void run() {
		long beginTime = System.currentTimeMillis();
		long addResult = 0;
		for(int i = 0; i < 10; i ++ ){
			for(int j = 0; j < 500000; j ++ ){
				Random random = new Random();
				random.nextInt();
				addResult = addResult + i;
			}
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("&&&&&&&&&&&&& thread2 use time = " + (endTime - beginTime));
	}
	
	// 高优先级线程总是先执行完，当线程优先级的等级差距很大时，谁先执行完和代码的调用顺序无关
	public static void main(String[] args){
		for(int i = 0; i < 5; i ++ ){
			Chapter01_C0_02 chapter01_c0_02 = new Chapter01_C0_02();
			chapter01_c0_02.setPriority(1);
			chapter01_c0_02.start();
			
			Chapter01_C0_03 chapter01_c0_03 = new Chapter01_C0_03();
			chapter01_c0_03.setPriority(10);
			chapter01_c0_03.start();
		}
	}
}
