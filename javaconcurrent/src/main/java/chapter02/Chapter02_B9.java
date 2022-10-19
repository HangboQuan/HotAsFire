package chapter02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 22-10-19 上午9:06
 */
public class Chapter02_B9 {
	private List list = new ArrayList();
	synchronized public void add(String data) {
		list.add(data);
	}

	synchronized public int getSize() {
		return list.size();
	}

}

class Chapter02_B9_01 {

	/**
	 * 原因是: ThreadA首先进入判断if(chapter02_b9.size() < 1), 然后就进行sleep, 此时ThreadB同样进行判断if(chapter02_b9.size()),
	 * 也符合判断条件,最终就是chapter02_b9这个list的size()为2
	 *
	 * result:
	 * listSize = 2
	 */
//	public void serviceMethod(Chapter02_B9 chapter02_b9, String data) {
//
//		/**
//		 * 实现的需求是 保证list总是只有一个元素
//		 */
//		if(chapter02_b9.getSize() < 1) {
//			try {
//				// 模拟从远程花费2s取数据
//				Thread.sleep(2000);
//				chapter02_b9.add(data);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
	/**
	 * result:
	 * listSize = 2
	 * 原因为虽然这里给方法加了synchronized, 但是这个synchronized是针对于chapter02_B9_01对象的, 并不能锁cahpter02_b9
	 */
	synchronized public void serviceMethod(Chapter02_B9 chapter02_b9, String data) {
		if(chapter02_b9.getSize() < 1) {
			try {
				// 模拟从远程花费2s取数据
				Thread.sleep(2000);
				chapter02_b9.add(data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/**
	 * result:
	 * listSize = 1
	 * @param chapter02_b9
	 * @param data
	 */
//	public void serviceMethod(Chapter02_B9 chapter02_b9, String data) {
//
//		synchronized (chapter02_b9){
//			if(chapter02_b9.getSize() < 1) {
//				try {
//					// 模拟从远程花费2s取数据
//					Thread.sleep(2000);
//					chapter02_b9.add(data);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//
//	}
}

class Chapter02_B9_02 extends Thread {

	private Chapter02_B9 chapter02_b9;

	public Chapter02_B9_02(Chapter02_B9 chapter02_b9) {
		this.chapter02_b9 = chapter02_b9;
	}

	@Override
	public void run() {
		Chapter02_B9_01 chapter02_b9_01 = new Chapter02_B9_01();
		chapter02_b9_01.serviceMethod(chapter02_b9, "A");
	}

}

class Chapter02_B9_03 extends Thread {

	private Chapter02_B9 chapter02_b9;

	public Chapter02_B9_03(Chapter02_B9 chapter02_b9) {
		this.chapter02_b9 = chapter02_b9;
	}

	@Override
	public void run() {
		Chapter02_B9_01 chapter02_b9_01 = new Chapter02_B9_01();
		chapter02_b9_01.serviceMethod(chapter02_b9, "B");
	}

}

class Chapter02_B9_04 {

	public static void main(String[] args) {


		Chapter02_B9 chapter02_b9 = new Chapter02_B9();

		Chapter02_B9_02 chapter02_b9_02 = new Chapter02_B9_02(chapter02_b9);
		chapter02_b9_02.setName("ThreadA");
		chapter02_b9_02.start();

		Chapter02_B9_03 chapter02_b9_03 = new Chapter02_B9_03(chapter02_b9);
		chapter02_b9_03.setName("ThreadB");
		chapter02_b9_03.start();

		try {
			Thread.sleep(6000);
			System.out.println("listSize = " + chapter02_b9.getSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
