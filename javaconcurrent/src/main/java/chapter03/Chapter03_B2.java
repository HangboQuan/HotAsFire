package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/10 7:10
 */
public class Chapter03_B2 {
	public static String value = "";
}

// 生产者
class Chapter03_B2_P {
	private String lock;
	
	public Chapter03_B2_P(String lock) {
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				
				// 生产者：如果当前值没有被消费，就等待，等待消费者去消费
				if(!Chapter03_B2.value.equalsIgnoreCase("")) {
					lock.wait();
				}
				
				// 生产者：如果当前值是空的，就生产一个product, 然后去通知
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println("set value = " + value);
				Chapter03_B2.value = value;
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// 消费者
class Chapter03_B2_C {
	private String lock;
	
	public Chapter03_B2_C(String lock) {
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				// 消费者：如果当前值空的，就说明生产者还没生产，于是就等待，等待生产者消费
				if(Chapter03_B2.value.equalsIgnoreCase("")) {
					lock.wait();
				}
				
				// 消费者：如果当前值不为空，说明生产者生产了，就需要消费一个，消费完一个之后又要通知
				// 生产者继续生产
				System.out.println("get value = " + Chapter03_B2.value);
				Chapter03_B2.value = "";
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B2_Thread_P extends Thread {
	
	private Chapter03_B2_P chapter03_b2_p;
	
	public Chapter03_B2_Thread_P(Chapter03_B2_P chapter03_b2_p) {
		this.chapter03_b2_p = chapter03_b2_p;
	}
	
	@Override
	public void run() {
		while(true) {
			chapter03_b2_p.setValue();
		}
	}
}

class Chapter03_B2_Thread_C extends Thread {

	private Chapter03_B2_C chapter03_b2_c;
	
	public Chapter03_B2_Thread_C(Chapter03_B2_C chapter03_b2_c) {
		this.chapter03_b2_c = chapter03_b2_c;
	}
	
	@Override
	public void run() {
		while(true) {
			chapter03_b2_c.getValue();
		}
	}
}

class Chapter03_B2_01 {

	public static void main(String[] args) {
		
		
		Chapter03_B2_P chapter03_b2_p = new Chapter03_B2_P(Chapter03_B2.value);
		Chapter03_B2_C chapter03_b2_c = new Chapter03_B2_C(Chapter03_B2.value);
		
		Chapter03_B2_Thread_P chapter03_b2_thread_p = new Chapter03_B2_Thread_P(chapter03_b2_p);
		Chapter03_B2_Thread_C chapter03_b2_thread_c = new Chapter03_B2_Thread_C(chapter03_b2_c);
		
		chapter03_b2_thread_p.start();
		chapter03_b2_thread_c.start();
	}
}