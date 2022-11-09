package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2022/11/9 17:47
 */
public class Chapter03_B1 {
	
	//实现生产者-消费者 生产者生产产品了，消费者才能消费
	public static List list = new ArrayList<>();
	
	public void add() {
		list.add("anyString");
		System.out.println("添加了一个元素");
	}
	
	public void subtract() {
		list.remove(0);
		System.out.println("减少了一个元素");
	}
}

class Chapter03_B1_Producter extends Thread {
	private Object lock;
	
	public Chapter03_B1_Producter(Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		
		try {
			synchronized (lock) {
				Chapter03_B1 chapter03_b1 = new Chapter03_B1();
				for(int i = 0; i < 100; i ++ ){
					chapter03_b1.add();
					lock.wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
class Chapter03_B1_Consumer extends Thread {
	private Object lock;
	
	public Chapter03_B1_Consumer(Object lock) {
		this.lock = lock;
	}
	
	@Override
	public void run() {
		synchronized (lock) {
			Chapter03_B1 chapter03_b1 = new Chapter03_B1();
			try {
				for(int i = 0; i < 100; i ++ ) {
					if(Chapter03_B1.list.size() != 0) {
						lock.notify();
						chapter03_b1.subtract();
						Thread.sleep(1000);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}

class Chapter03_B1_01 {

	public static void main(String[] args) {
		Object lock = new Object();
		
		Chapter03_B1_Producter chapter03_b1_producter = new Chapter03_B1_Producter(lock);
		chapter03_b1_producter.start();
		
		Chapter03_B1_Consumer chapter03_b1_consumer = new Chapter03_B1_Consumer(lock);
		chapter03_b1_consumer.start();
		
	}
}


