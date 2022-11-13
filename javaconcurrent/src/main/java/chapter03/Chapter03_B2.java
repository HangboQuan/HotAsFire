package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/10 7:10
 */
public class Chapter03_B2 {
	public static String value = "";
}


/**
 * 基本来说初始情况下:
 * 就两种情况，1是生产者线程先执行
 * result:
 * product_thread set value = 1668312207945_338500527534760
 * product_thread WAITING
 * consumer_thread get value = 1668312207945_338500527534760
 * consumer_thread WAITING
 * product_thread set value = 1668312207945_338500527914226
 * product_thread WAITING
 * consumer_thread get value = 1668312207945_338500527914226
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528065148
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528065148
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528228383
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528228383
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528377028
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528377028
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528490429
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528490429
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528605926
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528605926
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528692012
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528692012
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528787691
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528787691
 * consumer_thread WAITING
 * product_thread set value = 1668312207946_338500528879253
 * product_thread WAITING
 * consumer_thread get value = 1668312207946_338500528879253
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500528971949
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500528971949
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529083176
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529083176
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529181225
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529181225
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529323217
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529323217
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529448012
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529448012
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529602127
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529602127
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529761479
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529761479
 * consumer_thread WAITING
 * product_thread set value = 1668312207947_338500529909167
 * product_thread WAITING
 * consumer_thread get value = 1668312207947_338500529909167
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530034526
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530034526
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530141387
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530141387
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530256463
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530256463
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530364758
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530364758
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530500306
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530500306
 * consumer_thread WAITING
 * product_thread set value = 1668312207948_338500530633603
 * product_thread WAITING
 * consumer_thread get value = 1668312207948_338500530633603
 * consumer_thread WAITING
 * ...
 * 2.是消费者线程先执行
 * consumer_thread WAITING
 * product_thread set value = 1668312386965_338679547570346
 * product_thread WAITING
 * consumer_thread get value = 1668312386965_338679547570346
 * consumer_thread WAITING
 * product_thread set value = 1668312386965_338679547807117
 * product_thread WAITING
 * consumer_thread get value = 1668312386965_338679547807117
 * consumer_thread WAITING
 * product_thread set value = 1668312386966_338679547966902
 * product_thread WAITING
 * consumer_thread get value = 1668312386966_338679547966902
 * consumer_thread WAITING
 * product_thread set value = 1668312386966_338679548092535
 * product_thread WAITING
 * consumer_thread get value = 1668312386966_338679548092535
 * consumer_thread WAITING
 * product_thread set value = 1668312386966_338679548185022
 * product_thread WAITING
 * consumer_thread get value = 1668312386966_338679548185022
 * consumer_thread WAITING
 * product_thread set value = 1668312386966_338679548284875
 * product_thread WAITING
 * consumer_thread get value = 1668312386966_338679548284875
 * consumer_thread WAITING
 * product_thread set value = 1668312386966_338679548384657
 * product_thread WAITING
 *
 */

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
					System.out.println(Thread.currentThread().getName() + " WAITING");
					lock.wait();
				}
				
				// 生产者：如果当前值是空的，就生产一个product, 然后去通知
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println(Thread.currentThread().getName() + " set value = " + value);
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
					System.out.println(Thread.currentThread().getName() + " WAITING");
					lock.wait();
				}
				
				// 消费者：如果当前值不为空，说明生产者生产了，就需要消费一个，消费完一个之后又要通知
				// 生产者继续生产
				System.out.println(Thread.currentThread().getName() + " get value = " + Chapter03_B2.value);
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
			try {
//				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			try {
//				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter03_B2_01 {

	public static void main(String[] args) {
		
		
		Chapter03_B2_P chapter03_b2_p = new Chapter03_B2_P(Chapter03_B2.value);
		Chapter03_B2_C chapter03_b2_c = new Chapter03_B2_C(Chapter03_B2.value);
		
		Chapter03_B2_Thread_P chapter03_b2_thread_p = new Chapter03_B2_Thread_P(chapter03_b2_p);
		Chapter03_B2_Thread_C chapter03_b2_thread_c = new Chapter03_B2_Thread_C(chapter03_b2_c);

		chapter03_b2_thread_p.setName("product_thread");
		chapter03_b2_thread_c.setName("consumer_thread");
		chapter03_b2_thread_p.start();
		chapter03_b2_thread_c.start();


	}
}