package chapter03;

import java.util.Date;

/**
 * @author quanhangbo
 * @date 22-11-27 下午7:37
 */
public class Chapter03_C9 extends ThreadLocal {
	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}
}

class Chapter03_C9_Value {
	public static Chapter03_C9 t1 = new Chapter03_C9();
}

class Chapter03_C9_ThreadA extends Thread {

	@Override
	public void run() {
		try {
			for(int i = 0; i < 20; i ++ ) {
				if (Chapter03_C9_Value.t1.get() != null) {
					Chapter03_C9_Value.t1.set(new Date());
				}
				System.out.println("ThreadA " + Chapter03_C9_Value.t1.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_C9_ThreadB extends Thread {

	@Override
	public void run() {
		try {
			for(int i = 0; i < 20; i ++ ) {
				if (Chapter03_C9_Value.t1.get() != null) {
					Chapter03_C9_Value.t1.set(new Date());
				}
				System.out.println("ThreadB " + Chapter03_C9_Value.t1.get());
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_C9_01 {

	/**
	 * 首次调用的话是null, 通过继承ThreadLocal设置初始值
	 * java.lang.NullPointerException
	 * 	at chapter03.Chapter03_C9_ThreadA.run(Chapter03_C9.java:22)
	 * java.lang.NullPointerException
	 * 	at chapter03.Chapter03_C9_ThreadB.run(Chapter03_C9.java:41)
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_C9_ThreadA chapter03_c9_threadA = new Chapter03_C9_ThreadA();
			chapter03_c9_threadA.start();
			Thread.sleep(1000);

			Chapter03_C9_ThreadB chapter03_c9_threadB = new Chapter03_C9_ThreadB();
			chapter03_c9_threadB.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_C9_02 {
	public static Chapter03_C9 chapter03_c9 = new Chapter03_C9();

	public static void main(String[] args) {

		/**
		 * 1669550840883
		 * 1669550840883
		 */
		if (chapter03_c9.get() == null) {
			System.out.println("nothing");
		}
		System.out.println(chapter03_c9.get());
		System.out.println(chapter03_c9.get());
	}
}