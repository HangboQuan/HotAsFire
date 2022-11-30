package chapter03;

import java.util.Date;

/**
 * @author quanhangbo
 * @date 22-11-28 下午4:26
 */
public class Chapter03_D1 extends InheritableThreadLocal {

	@Override
	protected Object initialValue() {
		return new Date().getTime();
	}


	@Override
	protected Object childValue(Object parentValue) {
		return parentValue + " subThread append something";
	}
}

class Chapter03_D1_01 {
	public static Chapter03_D1 chapter03_d1 = new Chapter03_D1();
}


class Chapter03_D1_02 extends Thread {

	// 意思是这里继承了main线程(父线程)ThreadLocal的值
	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i ++ ) {
				System.out.println("chapter03_d0_02线程取值 = " + Chapter03_D1_01.chapter03_d1.get());
				Thread.sleep(100);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_D1_03 {

	/**
	 * 子线程从父线程(此处是Chapter03_D1_02 继承了main线程)继承了值之后
	 * result:
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * main 线程设置值 = 1669625542303
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * chapter03_d0_02线程取值 = 1669625542303 subThread append something
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			for(int i = 0; i < 10; i ++ ) {
				System.out.println("main 线程设置值 = " + Chapter03_D1_01.chapter03_d1.get());
				Thread.sleep(100);
			}

			Thread.sleep(5000);
			Chapter03_D1_02 chapter03_d1_02 = new Chapter03_D1_02();
			chapter03_d1_02.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

/**
 * ThreadLocal的问题
 * 1. 线程安全问题
 *
 * 2. 内存泄露
 *
 * 3. inheritableThreadLocal的线程安全问题
 *
 */
