package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-13 下午3:37
 */
public class Chapter03_B6 {
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
			this.notify();
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
			this.notify();
			System.out.println("pop = " + list.size());

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return returnValue;
	}
}

class Chapter03_B6_01_Product {
	private Chapter03_B6 chapter03_b6;

	public Chapter03_B6_01_Product(Chapter03_B6 chapter03_b6) {
		this.chapter03_b6 = chapter03_b6;
	}

	public void pushService() {
		chapter03_b6.push();
	}
}


class Chapter03_B6_01_Consumer {
	private Chapter03_B6 chapter03_b6;

	public Chapter03_B6_01_Consumer(Chapter03_B6 chapter03_b6) {
		this.chapter03_b6 = chapter03_b6;
	}

	public void popService() {
		System.out.println("pop = " + chapter03_b6.pop());
	}
}

class Chapter03_B6_01_Product_Thread extends Thread {
	private Chapter03_B6_01_Product chapter03_b6_01_product;

	public Chapter03_B6_01_Product_Thread(Chapter03_B6_01_Product chapter03_b6_01_product) {
		this.chapter03_b6_01_product = chapter03_b6_01_product;
	}

	@Override
	public void run() {
		while(true) {
			chapter03_b6_01_product.pushService();
		}
	}
}


class Chapter03_B6_01_Consumer_Thread extends Thread {
	private Chapter03_B6_01_Consumer chapter03_b6_01_consumer;

	public Chapter03_B6_01_Consumer_Thread(Chapter03_B6_01_Consumer chapter03_b6_01_consumer) {
		this.chapter03_b6_01_consumer = chapter03_b6_01_consumer;
	}

	@Override
	public void run() {
		while(true) {
			chapter03_b6_01_consumer.popService();
		}
	}
}


class Chapter03_B6_02 {
	/**
	 * 多个生产者 - 一个消费者
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_B6 chapter03_b6 = new Chapter03_B6();

		Chapter03_B6_01_Product chapter03_b6_01_productA = new Chapter03_B6_01_Product(chapter03_b6);
		Chapter03_B6_01_Product chapter03_b6_01_productB = new Chapter03_B6_01_Product(chapter03_b6);
		Chapter03_B6_01_Product chapter03_b6_01_productC = new Chapter03_B6_01_Product(chapter03_b6);
		Chapter03_B6_01_Product chapter03_b6_01_productD = new Chapter03_B6_01_Product(chapter03_b6);
		Chapter03_B6_01_Product chapter03_b6_01_productE = new Chapter03_B6_01_Product(chapter03_b6);


		Chapter03_B6_01_Consumer chapter03_b6_01_consumer = new Chapter03_B6_01_Consumer(chapter03_b6);
		Chapter03_B6_01_Product_Thread chapter03_b6_01_product_threadA = new Chapter03_B6_01_Product_Thread(chapter03_b6_01_productA);
		Chapter03_B6_01_Product_Thread chapter03_b6_01_product_threadB = new Chapter03_B6_01_Product_Thread(chapter03_b6_01_productB);
		Chapter03_B6_01_Product_Thread chapter03_b6_01_product_threadC = new Chapter03_B6_01_Product_Thread(chapter03_b6_01_productC);
		Chapter03_B6_01_Product_Thread chapter03_b6_01_product_threadD = new Chapter03_B6_01_Product_Thread(chapter03_b6_01_productD);
		Chapter03_B6_01_Product_Thread chapter03_b6_01_product_threadE = new Chapter03_B6_01_Product_Thread(chapter03_b6_01_productE);


		Chapter03_B6_01_Consumer_Thread chapter03_b6_01_consumer_thread = new Chapter03_B6_01_Consumer_Thread(chapter03_b6_01_consumer);

		chapter03_b6_01_product_threadA.start();
		chapter03_b6_01_product_threadB.start();
		chapter03_b6_01_product_threadC.start();
		chapter03_b6_01_product_threadD.start();
		chapter03_b6_01_product_threadE.start();

		chapter03_b6_01_consumer_thread.start();
		

	}
}

