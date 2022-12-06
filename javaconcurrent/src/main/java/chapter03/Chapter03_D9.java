package chapter03;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author quanhangbo
 * @date 2022/12/6 22:27
 */
public class Chapter03_D9 {
	// 自己实现一个队列，用于实现生产者-消费者模式
	public static void main(String[] args) {
		EventStorge storge = new EventStorge();
		Productor_Thread productor_thread = new Productor_Thread(storge);
		Consumer_Thread consumer_thread = new Consumer_Thread(storge);
		
		productor_thread.start();
		consumer_thread.start();
	}
}

class Consumer_Thread extends Thread {
	private EventStorge eventStorge;
	
	public Consumer_Thread(EventStorge storge) {
		this.eventStorge = storge;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i ++ ) {
			eventStorge.take();
		}
	}
}

class Productor_Thread extends Thread {
	private EventStorge eventStorge;
	
	public Productor_Thread(EventStorge storge) {
		this.eventStorge = storge;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i ++ ) {
			eventStorge.put();
		}
	}
}
class EventStorge {
	// 两个属性 最大属性|容器本身
	private int maxSize;
	private LinkedList<Date> storge;
	
	public EventStorge() {
		maxSize = 10;
		storge = new LinkedList<>();
	}
	
	// 提供两个方法， put()生产 take()消费
	
	public synchronized void put() {
		// 1. 队列满了的时候就等待 否则就生产
		while (storge.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storge.add(new Date());
		System.out.println("生产了" + storge.size() + "个产品");
		// 生产一个产品之后 就通知对方来消费
		notify();
	}
	
	public synchronized void take() {
		// 队列空的时候就等待 否则就消费
		while (storge.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("消费了" + storge.poll() + ", 还剩" + storge.size() + "个产品");
		// 消费一个产品之后，就通知对方生产
		notify();
	}
}
