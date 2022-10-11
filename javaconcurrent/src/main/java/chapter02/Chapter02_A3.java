package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/11 23:17
 */
public class Chapter02_A3 {
	
	synchronized public void methodA() {
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void methodB() {
		
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("END " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}
}

class Chapter02_A3_01 extends Thread{
	
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_01(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodA();
	}
	
}

class Chapter02_A3_02 extends Thread {
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_02(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodB();
	}
}

class Chapter02_A3_03 {
	
	public static void main(String[] args){
		Chapter02_A3 chapter02_a3 = new Chapter02_A3();
		Chapter02_A3_01 chapter02_a3_01 = new Chapter02_A3_01(chapter02_a3);
		Chapter02_A3_02 chapter02_a3_02 = new Chapter02_A3_02(chapter02_a3);
		
		chapter02_a3_01.setName("ThreadA");
		chapter02_a3_02.setName("ThreadB");
		
		chapter02_a3_01.start();
		chapter02_a3_02.start();
	}
}
