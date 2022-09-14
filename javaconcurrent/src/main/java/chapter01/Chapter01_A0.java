package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/14 15:17
 */
public class Chapter01_A0 extends Thread{

	/**
	 * a. 线程的启动
	 * b. 如何使线程暂停
	 * c. 如何使线程停止
	 * d. 线程的优先级
	 * e. 线程安全
	 *
	 * 进程: 是操作系统结构的基础，是一次程序的执行，是一个程序及其数据在处理机上顺序执行时发生的
	 * 活动，是程序在一个数据集合上运行的过程，它是系统进行资源分配和调度的一个独立单位，windows中的
	 * .exe就是一个进程
	 *
	 * 线程：是在进程中独立运行的子任务 极大提高了CPU的占用率
	 * 一个进程正在运行时至少有1个线程在运行
	 *
	 * 实现多线程编程的方式主要有两种：继承Thread类、实现Runnable接口
	 */
	@Override
	public void run(){
		super.run();
		System.out.println("MyThread");
	}

	/**
	 * 线程是一个子任务, CPU以不确定的方式或者说以随机的时间来调用线程中的run方法
	 *
	 * 如果多次调用start()方法，则会出现异常 Exception in thread "main" java.lang.IllegalThreadStateException
	 * @param args
	 */
	public static void main(String[] args){

		/**
		 * 运行结果：
		 * 运行结束
		 * MyThread
		 */
		Chapter01_A0 chapter01_a0 = new Chapter01_A0();
		chapter01_a0.start();
		System.out.println("运行结束");
	}
}
