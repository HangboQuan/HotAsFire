package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/18 7:35
 */
public class Chapter02_B7 {
	/**
	 * 同理锁不同对象，还是异步执行的
	 * result:
	 * serviceMethodA threadName = ThreadA begin task 1666050346315
	 * serviceMethodB threadName = ThreadB begin task 1666050346315
	 * serviceMethodB threadName = ThreadBend task 1666050346315
	 * serviceMethodA threadName = ThreadA end task 1666050349316
	 */
	private String anyString = new String();
	public void serviceMethodA() {
		synchronized (anyString) {
			try {
				System.out.println("serviceMethodA threadName = " + Thread.currentThread().getName() + " begin task " + System.currentTimeMillis() );
				Thread.sleep(3000);
				System.out.println("serviceMethodA threadName = " + Thread.currentThread().getName() + " end task " + System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized public void serviceMethodB() {
		System.out.println("serviceMethodB threadName = " + Thread.currentThread().getName() + " begin task " + System.currentTimeMillis() );
		System.out.println("serviceMethodB threadName = " + Thread.currentThread().getName() + "end task " + System.currentTimeMillis());
	}
}


class Chapter02_B7_01 extends Thread {
	
	private Chapter02_B7 chapter02_b7;
	
	public Chapter02_B7_01(Chapter02_B7 chapter02_b7) {
		this.chapter02_b7 = chapter02_b7;
	}
	
	@Override
	public void run() {
		chapter02_b7.serviceMethodA();
	}
}

class Chapter02_B7_02 extends Thread {

	private Chapter02_B7 chapter02_b7;
	
	public Chapter02_B7_02(Chapter02_B7 chapter02_b7) {
		this.chapter02_b7 = chapter02_b7;
	}
	
	@Override
	public void run() {
		chapter02_b7.serviceMethodB();
	}
}

class Chapter02_B7_03 {
	
	public static void main(String[] args) {
		Chapter02_B7 chapter02_b7 = new Chapter02_B7();
		
		Chapter02_B7_01 chapter02_b7_01 = new Chapter02_B7_01(chapter02_b7);
		chapter02_b7_01.setName("ThreadA");
		chapter02_b7_01.start();
		Chapter02_B7_02 chapter02_b7_02 = new Chapter02_B7_02(chapter02_b7);
		chapter02_b7_02.setName("ThreadB");
		chapter02_b7_02.start();
	}
}