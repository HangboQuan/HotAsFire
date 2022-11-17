package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-17 下午21:37
 */
public class Chapter03_B7 {
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

class Chapter03_B7_01_Product {
	private Chapter03_B7 Chapter03_B7;

	public Chapter03_B7_01_Product(Chapter03_B7 Chapter03_B7) {
		this.Chapter03_B7 = Chapter03_B7;
	}

	public void pushService() {
		Chapter03_B7.push();
	}
}


class Chapter03_B7_01_Consumer {
	private Chapter03_B7 Chapter03_B7;

	public Chapter03_B7_01_Consumer(Chapter03_B7 Chapter03_B7) {
		this.Chapter03_B7 = Chapter03_B7;
	}

	public void popService() {
		System.out.println("pop = " + Chapter03_B7.pop());
	}
}

class Chapter03_B7_01_Product_Thread extends Thread {
	private Chapter03_B7_01_Product Chapter03_B7_01_product;

	public Chapter03_B7_01_Product_Thread(Chapter03_B7_01_Product Chapter03_B7_01_product) {
		this.Chapter03_B7_01_product = Chapter03_B7_01_product;
	}

	@Override
	public void run() {
		while(true) {
			Chapter03_B7_01_product.pushService();
		}
	}
}


class Chapter03_B7_01_Consumer_Thread extends Thread {
	private Chapter03_B7_01_Consumer Chapter03_B7_01_consumer;

	public Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_Consumer Chapter03_B7_01_consumer) {
		this.Chapter03_B7_01_consumer = Chapter03_B7_01_consumer;
	}

	@Override
	public void run() {
		while(true) {
			Chapter03_B7_01_consumer.popService();
		}
	}
}


class Chapter03_B7_02 {
	/**
	 * 多个生产者 - 一个消费者
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_B7 Chapter03_B7 = new Chapter03_B7();

		Chapter03_B7_01_Product Chapter03_B7_01_productA = new Chapter03_B7_01_Product(Chapter03_B7);
		Chapter03_B7_01_Product Chapter03_B7_01_productB = new Chapter03_B7_01_Product(Chapter03_B7);
		Chapter03_B7_01_Product Chapter03_B7_01_productC = new Chapter03_B7_01_Product(Chapter03_B7);
		Chapter03_B7_01_Product Chapter03_B7_01_productD = new Chapter03_B7_01_Product(Chapter03_B7);
		Chapter03_B7_01_Product Chapter03_B7_01_productE = new Chapter03_B7_01_Product(Chapter03_B7);


		Chapter03_B7_01_Consumer Chapter03_B7_01_consumer = new Chapter03_B7_01_Consumer(Chapter03_B7);
		Chapter03_B7_01_Product_Thread Chapter03_B7_01_product_threadA = new Chapter03_B7_01_Product_Thread(Chapter03_B7_01_productA);
		Chapter03_B7_01_Product_Thread Chapter03_B7_01_product_threadB = new Chapter03_B7_01_Product_Thread(Chapter03_B7_01_productB);
		Chapter03_B7_01_Product_Thread Chapter03_B7_01_product_threadC = new Chapter03_B7_01_Product_Thread(Chapter03_B7_01_productC);
		Chapter03_B7_01_Product_Thread Chapter03_B7_01_product_threadD = new Chapter03_B7_01_Product_Thread(Chapter03_B7_01_productD);
		Chapter03_B7_01_Product_Thread Chapter03_B7_01_product_threadE = new Chapter03_B7_01_Product_Thread(Chapter03_B7_01_productE);


		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadA = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadB = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadC = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadD = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadE = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadF = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);
		Chapter03_B7_01_Consumer_Thread Chapter03_B7_01_consumer_threadG = new Chapter03_B7_01_Consumer_Thread(Chapter03_B7_01_consumer);

		Chapter03_B7_01_product_threadA.start();
		Chapter03_B7_01_product_threadB.start();
		Chapter03_B7_01_product_threadC.start();
		Chapter03_B7_01_product_threadD.start();
		Chapter03_B7_01_product_threadE.start();

		Chapter03_B7_01_consumer_threadA.start();
		Chapter03_B7_01_consumer_threadB.start();
		Chapter03_B7_01_consumer_threadC.start();
		Chapter03_B7_01_consumer_threadD.start();
		Chapter03_B7_01_consumer_threadE.start();
		Chapter03_B7_01_consumer_threadF.start();
		Chapter03_B7_01_consumer_threadG.start();
		
		
		
	}
}

