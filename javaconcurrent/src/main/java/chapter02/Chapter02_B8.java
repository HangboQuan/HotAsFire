package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-10-18 上午9:10
 */
public class Chapter02_B8 {
	private List list = new ArrayList();
	synchronized public void add(String username) {
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " 执行了add方法！");
		list.add(username);
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " 退出了add方法！");
	}

	synchronized public int getSize() {
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " 执行了getSize方法！");
		int sizeValue = list.size();
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " 退出了getSize方法！");
		return sizeValue;
	}
}

class Chapter02_B8_01 extends Thread {

	private Chapter02_B8 chapter02_b8;

	public Chapter02_B8_01(Chapter02_B8 chapter02_b8) {
		this.chapter02_b8 = chapter02_b8;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100000; i ++ ) {
			chapter02_b8.add("ThreadA" + (i + 1));
		}
	}
}

class Chapter02_B8_02 extends Thread {

	private Chapter02_B8 chapter02_b8;

	public Chapter02_B8_02(Chapter02_B8 chapter02_b8) {
		this.chapter02_b8 = chapter02_b8;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100000; i ++ ) {
			chapter02_b8.add("ThreadB" + (i + 1));
		}
	}
}


class Chapter02_B8_03 {
	/**
	 * 同步代码块的代码是同步打印的，即 执行和退出 是成对出现的
	 * 线程A和线程B 执行异步，就有可能出现脏读的环境
	 *
	 * 线程执行的方法的顺序不确定 当A和B两个线程执行带有分支判断的方法时 就会出现逻辑上的错误 脏读
	 * @param args
	 */

	public static void main(String[] args) {
		Chapter02_B8 chapter02_b8 = new Chapter02_B8();

		Chapter02_B8_01 chapter02_b8_01 = new Chapter02_B8_01(chapter02_b8);
		chapter02_b8_01.setName("ThreadA");
		chapter02_b8_01.start();

		Chapter02_B8_02 chapter02_b8_02 = new Chapter02_B8_02(chapter02_b8);
		chapter02_b8_02.setName("ThreadB");
		chapter02_b8_02.start();
	}
}