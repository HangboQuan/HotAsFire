package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/8 20:55
 */
public class Chapter02_A1 {
	
	private int num;
	public void addI(String username) {
		try{
			if("a".equalsIgnoreCase(username)) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(2000);
			} else {
				num = 200;
				System.out.println("b set over");
			}
			System.out.println("username = " + username + " num = " + num);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

class Chapter02_A1_01 extends Thread {
	
	private Chapter02_A1 chapter02_a1;
	
	public Chapter02_A1_01(Chapter02_A1 chapter02_a1) {
		super.run();
		this.chapter02_a1 = chapter02_a1;
	}
	
	@Override
	public void run() {
		chapter02_a1.addI("a");
	}
}

class Chapter02_A1_02 extends Thread {

	private Chapter02_A1 chapter02_a1;
	
	public Chapter02_A1_02(Chapter02_A1 chapter02_a1) {
		super.run();
		this.chapter02_a1 = chapter02_a1;
	}
	
	@Override
	public void run() {
		chapter02_a1.addI("b");
	}
}

class Chapter02_A1_03 {
	
	public static void main(String[] args){
		
		/**
		 * result:
		 * a set over
		 * b set over
		 * username = b num = 200
		 * username = a num = 200
		 *
		 * 两个线程同时访问一个没有同步的方法，如果两个线程同时操作业务对象中的
		 * 实例变量，则可能出现非线程安全问题
		 *
		 * 可以在非同步方法加synchronized
		 * result:
		 * a set over
		 * username = a num = 100
		 * b set over
		 * username = b num = 200
		 */
		Chapter02_A1 chapter02_a1 = new Chapter02_A1();
		
		Chapter02_A1_01 chapter02_a1_01 = new Chapter02_A1_01(chapter02_a1);
		chapter02_a1_01.start();
		
		Chapter02_A1_02 chapter02_a1_02 = new Chapter02_A1_02(chapter02_a1);
		chapter02_a1_02.start();
	}
	
	
	
}
