package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/10 9:38
 */
public class Chapter02_A2 {
	
	
	synchronized public void methodA() {
		
		try{
			System.out.println("begin currentThread name:" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

class Chapter02_A2_01 extends Thread{
	
	private Chapter02_A2 chapter02_a2;
	
	public Chapter02_A2_01(Chapter02_A2 chapter02_a2) {
		this.chapter02_a2 = chapter02_a2;
	}
	
	@Override
	public void run() {
		chapter02_a2.methodA();
	}
}

class Chapter02_A2_02 extends Thread{

	private Chapter02_A2 chapter02_a2;
	
	public Chapter02_A2_02(Chapter02_A2 chapter02_a2) {
		this.chapter02_a2 = chapter02_a2;
	}
	
	@Override
	public void run() {
		chapter02_a2.methodA();
	}
}

class Chapter02_A2_03 {
	
	public static void main(String[] args){
		
		/**
		 * 只有共享资源的读写才需要同步化，非共享资源没必要同步化(如方法内部的私有变量)
		 * 未加synchronized同步
		 * begin currentThread name:ThreadA
		 * begin currentThread name:ThreadB
		 * end
		 * end
		 *
		 * 加上synchronized同步锁
		 * begin currentThread name:ThreadA
		 * end
		 * begin currentThread name:ThreadB
		 * end
		 */
		Chapter02_A2 chapter02_a2 = new Chapter02_A2();
		
		Chapter02_A2_01 chapter02_a2_01= new Chapter02_A2_01(chapter02_a2);
		chapter02_a2_01.setName("ThreadA");
		chapter02_a2_01.start();
		
		Chapter02_A2_02 chapter02_a2_02 = new Chapter02_A2_02(chapter02_a2);
		chapter02_a2_02.setName("ThreadB");
		chapter02_a2_02.start();
		
	}
}