package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-10 下午9:00
 */
public class Chapter03_B3 {
	public static String value = "";
}

/**
 * 多生产-多消费: 假死
 *
 * 假死现象是线程进入waiting等待状态，如果全部线程都进入了waiting状态，则程序就不在执行任何业务功能了，整个项目呈停滞状态
 */

/**
 * 由于可能出现生产者线程唤醒生产者线程，消费者唤醒消费者线程，假如这种比例失衡的情况下，就会出现假死
 *
 * 为了解决该问题，只需将生产者消费者notify()->notifyAll()这样就解决了
 *
 * result:
 * 生产者 生产者线程1 RUNNABLE了  line1   生产者1进行了生产，生产完毕后通过过早，释放锁，又重新进入到while(true)
 * 生产者 生产者线程1 WAITING了*  line2   生产者1进入while(true) -> while(value不空) -> 发现产品没有被消费 -> 线程生产者1等待
 * 生产者 生产者线程2 WAITING了*  line3   生产者2进入while(true) -> while(value不空）-> 发现产品没有被消费 -> 线程生产者2等待
 * 消费者 消费者线程2 RUNNABLE了  line4   消费者2进入了消费，消费完毕后唤醒line7的生产者线程1继续生产 value = ''
 * 消费者 消费者线程2 WAITING了&  line5   消费者2进入while(true) -> while(value为空）-> 发现产品被消费了 -> 线程消费者2等待
 * 消费者 消费者线程1 WAITING了&  line6   消费者1进入while(true) -> while(value为空）-> 发现产品被消费了 -> 线程消费者1等待
 * 生产者 生产者线程1 RUNNABLE了  line7   由第4行的消费者唤醒之后，开始生产，唤醒line9的线程生产者2
 * 生产者 生产者线程1 WAITING了*  line8   生产者1进入while(true) -> while(value不空) -> 发现产品没有被消费 -> 线程生产者1等待
 * 生产者 生产者线程2 WAITING了*  line9   生产者2被line7唤醒之后，while(true) -> while(value不空） -> 发现产品没有被消费 -> 线程生产者2等待
 * main -------- RUNNABLE
 * Monitor Ctrl-Break -------- RUNNABLE
 * 生产者线程1 -------- WAITING
 * 消费者线程1 -------- WAITING
 * 生产者线程2 -------- WAITING
 * 消费者线程2 -------- WAITING
 */
class Chapter03_B3_01 {
	private String lock;

	public Chapter03_B3_01(String lock) {
		this.lock = lock;
	}

	public void setValue() {

		try {
			synchronized (lock) {
				while(!Chapter03_B3.value.equalsIgnoreCase("")) {
					System.out.println("生产者 " +  Thread.currentThread().getName() + " WAITING了*");
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
					System.out.println("消费者 " + Thread.currentThread().getName() + " WAITING了&");
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
	
	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		Chapter03_B3_01 chapter03_b3_01 = new Chapter03_B3_01(lock);
		Chapter03_B3_02 chapter03_b3_02 = new Chapter03_B3_02(lock);
		
		Chapter03_B3_02_ThreadA[] chapter03_b3_02_threadAS = new Chapter03_B3_02_ThreadA[2];
		Chapter03_B3_02_ThreadB[] chapter03_b3_02_threadBS = new Chapter03_B3_02_ThreadB[2];

		// 启动两个生产者线程，两个消费者线程
		for(int i = 0; i < 2; i ++ ) {
			chapter03_b3_02_threadAS[i] = new Chapter03_B3_02_ThreadA(chapter03_b3_01);
			chapter03_b3_02_threadAS[i].setName("生产者线程" + (i + 1));
			
			chapter03_b3_02_threadBS[i] = new Chapter03_B3_02_ThreadB(chapter03_b3_02);
			chapter03_b3_02_threadBS[i].setName("消费者线程" + (i + 1));
			
			chapter03_b3_02_threadAS[i].start();
			chapter03_b3_02_threadBS[i].start();

			
		}

		Thread.sleep(5000);

		// getThreadGroup(): returns the thread group to which this thread belongs
		// activeCount(): returns an estimate(估算值) of the number of active threads in this thread group and its subgroups
		// enumerate(): copies into the specified array every active thread in this thread group and its subgroups
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);
		for(int i = 0; i < threadArray.length; i ++ ) {
			System.out.println(threadArray[i].getName() + " -------- " + threadArray[i].getState());
		}
	}
}