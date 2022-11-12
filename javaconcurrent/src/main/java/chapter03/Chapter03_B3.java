package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-10 下午9:00
 */
public class Chapter03_B3 {
	public static String value = "";
}


class Chapter03_B3_01 {
	private String lock;

	public Chapter03_B3_01(String lock) {
		this.lock = lock;
	}

	public void setValue() {

		try {
			synchronized (lock) {
				while(!Chapter03_B3.value.equalsIgnoreCase("")) {
					System.out.println("生产者 " +  Thread.currentThread().getName() + "waiting了*");
					lock.wait();
				}
				System.out.println("生产者 " + Thread.currentThread().getName() + " RUNNABLE了");
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				Chapter03_B3.value = value;
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_B3_02 {
	private String lock;
	
	public Chapter03_B3_02(String lock) {
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				while(Chapter03_B3.value.equalsIgnoreCase("")){
					System.out.println("消费者 " + Thread.currentThread().getName() + "waiting了&");
					lock.wait();
				}
				System.out.println("消费者 " + Thread.currentThread().getName() + " RUNNABLE了");
				Chapter03_B3.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B3_02_ThreadA extends Thread {
	
	private Chapter03_B3_01 chapter03_b3_01;
	
	public Chapter03_B3_02_ThreadA(Chapter03_B3_01 chapter03_b3_01) {
		this.chapter03_b3_01 = chapter03_b3_01;
	}
	
	@Override
	public void run() {
		while(true) {
			chapter03_b3_01.setValue();
		}
	}
}

class Chapter03_B3_02_ThreadB extends Thread {

	private Chapter03_B3_02 chapter03_b3_02;
	
	public Chapter03_B3_02_ThreadB(Chapter03_B3_02 chapter03_b3_02) {
		this.chapter03_b3_02 = chapter03_b3_02;
	}
	
	@Override
	public void run() {
		while(true) {
			chapter03_b3_02.getValue();
		}
	}
}

class Chapter03_B3_03 {
	
	public static void main(String[] args) {
		String lock = new String("");
		Chapter03_B3_01 chapter03_b3_01 = new Chapter03_B3_01(lock);
		Chapter03_B3_02 chapter03_b3_02 = new Chapter03_B3_02(lock);
		
		Chapter03_B3_02_ThreadA[] chapter03_b3_02_threadAS = new Chapter03_B3_02_ThreadA[2];
		Chapter03_B3_02_ThreadB[] chapter03_b3_02_threadBS = new Chapter03_B3_02_ThreadB[2];
		
		for(int i = 0; i < 2; i ++ ) {
			chapter03_b3_02_threadAS[i] = new Chapter03_B3_02_ThreadA(chapter03_b3_01);
			chapter03_b3_02_threadAS[i].setName("生产者 " + (i + 1));
			
			chapter03_b3_02_threadBS[i] = new Chapter03_B3_02_ThreadB(chapter03_b3_02);
			chapter03_b3_02_threadBS[i].setName("消费者 " + (i + 1));
			
			chapter03_b3_02_threadAS[i].start();
			chapter03_b3_02_threadBS[i].start();
			
		}
	}
}