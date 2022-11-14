package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-13 下午3:37
 */
public class Chapter03_B5 {
	private List list = new ArrayList<>();

	synchronized public void push() {
		try {
			/*if(list.size() == 1) {
				this.wait();
			}*/
			while(list.size() == 1) {
				this.wait();
			}
			list.add("anyString = " + Math.random());
			this.notifyAll();
			System.out.println("push = " + list.size());
		} catch (Exception e) {

		}
	}

	synchronized public String pop() {
		String returnValue = "";
		try {
			/*if(list.size() == 0) {
				System.out.println("pop操作中的: " + Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}*/
			while(list.size() == 0) {
				System.out.println("pop操作中的: " + Thread.currentThread().getName() + " 线程呈wait状态");
				this.wait();
			}
			returnValue = "" + list.get(0);
			list.remove(0);
			this.notifyAll();
			System.out.println("pop = " + list.size());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}

class Chapter03_B5_01_Product {
	private Chapter03_B5 chapter03_b5;

	public Chapter03_B5_01_Product(Chapter03_B5 chapter03_b5) {
		this.chapter03_b5 = chapter03_b5;
	}

	public void pushService() {
		chapter03_b5.push();
	}
}


class Chapter03_B5_01_Consumer {
	private Chapter03_B5 chapter03_b5;

	public Chapter03_B5_01_Consumer(Chapter03_B5 chapter03_b5) {
		this.chapter03_b5 = chapter03_b5;
	}

	public void popService() {
		System.out.println("pop = " + chapter03_b5.pop());
	}
}

class Chapter03_B5_01_Product_Thread extends Thread {
	private Chapter03_B5_01_Product chapter03_b5_01_product;

	public Chapter03_B5_01_Product_Thread(Chapter03_B5_01_Product chapter03_b5_01_product) {
		this.chapter03_b5_01_product = chapter03_b5_01_product;
	}

	@Override
	public void run() {
		while(true) {
			chapter03_b5_01_product.pushService();
		}
	}
}


class Chapter03_B5_01_Consumer_Thread extends Thread {
	private Chapter03_B5_01_Consumer chapter03_b5_01_consumer;

	public Chapter03_B5_01_Consumer_Thread(Chapter03_B5_01_Consumer chapter03_b5_01_consumer) {
		this.chapter03_b5_01_consumer = chapter03_b5_01_consumer;
	}

	@Override
	public void run() {
		while(true) {
			chapter03_b5_01_consumer.popService();
		}
	}
}


class Chapter03_B5_02 {
	public static void main(String[] args) {
		/**
		 * 模拟一个生产者，一个消费者的情况
		 */
		Chapter03_B5 chapter03_b5 = new Chapter03_B5();

		Chapter03_B5_01_Product chapter03_b5_01_product = new Chapter03_B5_01_Product(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumer = new Chapter03_B5_01_Consumer(chapter03_b5);

		Chapter03_B5_01_Product_Thread chapter03_b5_01_product_thread = new Chapter03_B5_01_Product_Thread(chapter03_b5_01_product);
		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_thread = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumer);

		chapter03_b5_01_product_thread.start();
		chapter03_b5_01_consumer_thread.start();
	}
}

class Chapter03_B5_03 {
	/**
	 * 模拟一个生产者，多个消费者的情况
	 *
	 * 在什么条件下等待 -> if判断下
	 * push = 1
	 * pop = 0
	 * pop = anyString = 0.4014759916734276
	 * pop操作中的: Thread-4 线程呈wait状态
	 * pop操作中的: Thread-3 线程呈wait状态
	 * pop操作中的: Thread-2 线程呈wait状态
	 * pop操作中的: Thread-1 线程呈wait状态
	 * pop操作中的: Thread-5 线程呈wait状态
	 * push = 1
	 * pop = 0
	 * pop = anyString = 0.7050277591755014
	 * Exception in thread "Thread-3" pop操作中的: Thread-4 线程呈wait状态
	 * java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
	 * 	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
	 * 	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
	 * 	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
	 * 	at java.base/java.util.Objects.checkIndex(Objects.java:372)
	 * 	at java.base/java.util.ArrayList.get(ArrayList.java:458)
	 * 	at chapter03.Chapter03_B5.pop(Chapter03_B5.java:33)
	 * 	at chapter03.Chapter03_B5_01_Consumer.popService(Chapter03_B5.java:66)
	 * 	at chapter03.Chapter03_B5_01_Consumer_Thread.run(Chapter03_B5.java:96)
	 *
	 *
	 * 	在什么条件下等待 -> while 会出现假死的情况 （应该改notify -> notifyAll）
	 *
	 * push = 1
	 * pop = 0
	 * pop = anyString = 0.6730900398695394
	 * pop操作中的: Thread-5 线程呈wait状态
	 * pop操作中的: Thread-4 线程呈wait状态
	 * pop操作中的: Thread-3 线程呈wait状态
	 * pop操作中的: Thread-1 线程呈wait状态
	 * pop操作中的: Thread-2 线程呈wait状态
	 * push = 1
	 * pop = 0
	 * pop = anyString = 0.3462547211956438
	 * pop操作中的: Thread-4 线程呈wait状态
	 * pop操作中的: Thread-5 线程呈wait状态
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_B5 chapter03_b5 = new Chapter03_B5();

		Chapter03_B5_01_Product chapter03_b5_01_product = new Chapter03_B5_01_Product(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumerA = new Chapter03_B5_01_Consumer(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumerB = new Chapter03_B5_01_Consumer(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumerC = new Chapter03_B5_01_Consumer(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumerD = new Chapter03_B5_01_Consumer(chapter03_b5);
		Chapter03_B5_01_Consumer chapter03_b5_01_consumerE = new Chapter03_B5_01_Consumer(chapter03_b5);

		Chapter03_B5_01_Product_Thread chapter03_b5_01_product_thread = new Chapter03_B5_01_Product_Thread(chapter03_b5_01_product);
		chapter03_b5_01_product_thread.start();

		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_threadA = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumerA);
		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_threadB = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumerB);
		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_threadC = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumerC);
		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_threadD = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumerD);
		Chapter03_B5_01_Consumer_Thread chapter03_b5_01_consumer_threadE = new Chapter03_B5_01_Consumer_Thread(chapter03_b5_01_consumerE);

		chapter03_b5_01_consumer_threadA.start();
		chapter03_b5_01_consumer_threadB.start();
		chapter03_b5_01_consumer_threadC.start();
		chapter03_b5_01_consumer_threadD.start();
		chapter03_b5_01_consumer_threadE.start();
	}
}