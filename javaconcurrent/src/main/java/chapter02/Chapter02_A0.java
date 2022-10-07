package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/7 22:46
 */
public class Chapter02_A0 {
	
	public void addI(String username) {
		try{
			int num = 0;
			if("a".equalsIgnoreCase(username)) {
				num = 100;
				System.out.println("a set over");
				Thread.sleep(1000);
			}else{
				num = 200;
				System.out.println("b set over");
			}
			System.out.println("username = " + username + " num = " + num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
class Chapter02_A0_01 extends Thread {
	
	private Chapter02_A0 chapter02_a0;
	public Chapter02_A0_01(Chapter02_A0 chapter02_a0) {
		super();
		this.chapter02_a0 = chapter02_a0;
	}
	
	@Override
	public void run() {
		super.run();
		chapter02_a0.addI("a");
	}
}

class Chapter02_A0_02 extends Thread {
	private Chapter02_A0 chapter02_a0;
	
	public Chapter02_A0_02(Chapter02_A0 chapter02_a0) {
		super();
		this.chapter02_a0 = chapter02_a0;
	}
	
	@Override
	public void run() {
		super.run();
		chapter02_a0.addI("b");
	}
}


/**
 * 非线程安全：多个线程对同一个对象中的实例变量进行并发访问时发生，产生的后果是脏读，
 * 也就是取到的数据其实是被更改过的
 *
 * 非线程安全问题存在于实例变量中，如果是方法内部的私有变量，则不存在非线程安全问题
 *
 * 线程安全：获得的实例变量的值是经过同步处理的，不会出现脏读的线程
 */
class Chapter02_A0_03 {

	/**
	 * result:
	 * a set over
	 * b set over
	 * username = b num = 200
	 * username = a num = 100
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_A0 chapter02_a0 = new Chapter02_A0();
		
		Chapter02_A0_01 chapter02_a0_01 = new Chapter02_A0_01(chapter02_a0);
		chapter02_a0_01.start();
		
		Chapter02_A0_02 chapter02_a0_02 = new Chapter02_A0_02(chapter02_a0);
		chapter02_a0_02.start();
	}
	
	
}