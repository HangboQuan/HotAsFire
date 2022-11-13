package chapter03;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-11-13 下午12:51
 */
public class Chapter03_B4 {
	private List<String> list = new ArrayList<>(1);

	public void push(Object lock) {
		try {
			synchronized (lock) {
//				System.out.println(lock);
				if(list.size() == 1) {
					lock.wait();
				}
				list.add("anything");
				System.out.println("生产了一个元素" + list.size());
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void pop(Object lock) {
		try {
			synchronized (lock) {
//				System.out.println(lock);
				if(list.size() == 0) {
					lock.wait();
				}

				/**
				 * 疑问: 代码走到这里的时候，list.size() == 0? 导致remove(0)抛异常，暂时未找到原因
				 */
				System.out.println(list.size());
				list.remove(0);
				System.out.println("消费了一个元素");
				lock.notify();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_B4_01_P extends Thread {

	private Object lock;

	public Chapter03_B4_01_P(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while(true) {
			Chapter03_B4 chapter03_b4 = new Chapter03_B4();
			chapter03_b4.push(lock);
		}
	}
}


class Chapter03_B4_01_C extends Thread {

	private Object lock;

	public Chapter03_B4_01_C(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		while(true) {
			Chapter03_B4 chapter03_b4 = new Chapter03_B4();
			chapter03_b4.pop(lock);
		}
	}
}

class Chapter03_B4_02 {

	public static void main(String[] args) {
		Object lock = new Object();

		Chapter03_B4_01_P chapter03_b4_01_p = new Chapter03_B4_01_P(lock);
		Chapter03_B4_01_C chapter03_b4_01_c = new Chapter03_B4_01_C(lock);

		chapter03_b4_01_p.start();
		chapter03_b4_01_c.start();
	}

}