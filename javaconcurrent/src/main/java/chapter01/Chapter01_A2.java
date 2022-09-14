package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/14 21:26
 */
public class Chapter01_A2 extends Thread{
	private int i;
	
	public Chapter01_A2(int i) {
		super();
		this.i = i;
	}
	
	@Override
	public void run(){
		System.out.println(i);
	}
	
	
	public static void main(String[] args){
		/**
		 * result: 说明线程启动顺序和start()执行顺序无关
		 0
		 3
		 4
		 5
		 2
		 1
		 9
		 11
		 10
		 7
		 6
		 8
		 */
		Chapter01_A2 a0 = new Chapter01_A2(0);
		Chapter01_A2 a1 = new Chapter01_A2(1);
		Chapter01_A2 a2 = new Chapter01_A2(2);
		Chapter01_A2 a3 = new Chapter01_A2(3);
		Chapter01_A2 a4 = new Chapter01_A2(4);
		Chapter01_A2 a5 = new Chapter01_A2(5);
		Chapter01_A2 a6 = new Chapter01_A2(6);
		Chapter01_A2 a7 = new Chapter01_A2(7);
		Chapter01_A2 a8 = new Chapter01_A2(8);
		Chapter01_A2 a9 = new Chapter01_A2(9);
		Chapter01_A2 a10 = new Chapter01_A2(10);
		Chapter01_A2 a11 = new Chapter01_A2(11);
		
		a0.start();
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
		a6.start();
		a7.start();
		a8.start();
		a9.start();
		a10.start();
		a11.start();
		
	}
	
}
