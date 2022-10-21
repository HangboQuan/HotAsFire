package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-21 下午1:30
 */
public class Chapter02_C4 {

//	public static void print(String stringParam) {
//
//		try {
//			synchronized (stringParam) {
//				while(true) {
//					System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
//					Thread.sleep(2000);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void print(Object object) {

		try {
		 	synchronized (object) {
		 		while(true) {
		 			System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		 			Thread.sleep(2000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_C4_01 extends Thread {

	private Chapter02_C4 chapter02_c4;

	public Chapter02_C4_01(Chapter02_C4 chapter02_c4) {
		this.chapter02_c4 = chapter02_c4;
	}

	@Override
	public void run() {
		chapter02_c4.print(new Object());
	}
}


class Chapter02_C4_02 extends Thread {

	private Chapter02_C4 chapter02_c4;

	public Chapter02_C4_02(Chapter02_C4 chapter02_c4) {
		this.chapter02_c4 = chapter02_c4;
	}

	@Override
	public void run() {
		chapter02_c4.print("AA");
	}
}

class Chapter02_C4_03 {
	/**
	 * JVM的String 常量池缓存的功能 String a = "a", String b = "a" System.out.println(a == b) //结果为true
	 *
	 * synchronized()锁字符串，由于字符串的常量池缓存功能，造成锁住的是同一个对象，B线程无法执行
	 * 如果将这里的ThreadA调用 print("AA") ThreadB调用 print("BB") 则锁住的是两个不同的对象，那么就可以交替打印A 和 B了
	 *
	 * 正如上述原因: 同步代码块都不使用String作为锁对象，改用其他，比如new Object()实例化一个Object对象，但并不放在缓存中
	 * @param args
	 */

	public static void main(String[] args) {
		Chapter02_C4 chapter02_c4 = new Chapter02_C4();

		Chapter02_C4_01 chapter02_c4_01 = new Chapter02_C4_01(chapter02_c4);
		chapter02_c4_01.setName("ThreadA");
		chapter02_c4_01.start();

		Chapter02_C4_02 chapter02_c4_02 = new Chapter02_C4_02(chapter02_c4);
		chapter02_c4_02.setName("ThreadB");
		chapter02_c4_02.start();


	}
}