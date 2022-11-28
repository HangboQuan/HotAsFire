package chapter03;

import java.util.Date;

/**
 * @author quanhangbo
 * @date 22-11-28 上午9:19
 */
public class Chapter03_D0 extends InheritableThreadLocal {

	// 使用inheritableThreadLocal 可以让子线程从父线程中取得值
	@Override
	protected Object initialValue() {
		// returns the numbers of milliseconds since January 1,1970, 00:00:00 represented by this Date Object
		return new Date().getTime();
	}

}

class Chapter03_D0_01 {
	public static Chapter03_D0 chapter03_d0 = new Chapter03_D0();
}


class Chapter03_D0_02 extends Thread {

	// 意思是这里继承了main线程(父线程)ThreadLocal的值
	@Override
	public void run() {
		try {
			for(int i = 0; i < 20; i ++ ) {
				System.out.println("chapter03_d0_02线程取值 = " + Chapter03_D0_01.chapter03_d0.get());
				Thread.sleep(100);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


class Chapter03_D0_03 {


	public static void main(String[] args) {
		try {
			for(int i = 0; i < 20; i ++ ) {
				System.out.println("main 线程设置值 = " + Chapter03_D0_01.chapter03_d0.get());
				Thread.sleep(100);
			}

			Thread.sleep(5000);
			Chapter03_D0_02 chapter03_d0_02 = new Chapter03_D0_02();
			chapter03_d0_02.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}