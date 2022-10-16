package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-16 下午5:32
 */
public class Chapter02_A7 {
	synchronized public void testMethod() {
		if (Thread.currentThread().getName().equals("ThreadA")) {
			System.out.println("ThreadName = " + Thread.currentThread().getName() + ": begin = " + System.currentTimeMillis());
			int i = 1;

			while(i < 500000) {
				if(Integer.parseInt(("" + Math.random()).substring(2, 6)) > 9998) {
					System.out.println("ThreadName = " + Thread.currentThread().getName() + ": exception = " + System.currentTimeMillis());
					Integer.parseInt("a");
					i ++;
				}
			}
		} else {
			System.out.println("ThreadName B = " + Thread.currentThread().getName() + ":" + System.currentTimeMillis());
			int i = 10;
			while (i > 0) {
				System.out.println("ThreadName: " + Thread.currentThread().getName() + " : " + i);
				i --;
			}
		}
	}
}

class Chapter02_A7_01 extends Thread {

	private Chapter02_A7 chapter02_a7;


	public Chapter02_A7_01(Chapter02_A7 chapter02_a7) {
		this.chapter02_a7 = chapter02_a7;
	}

	@Override
	public void run() {
		chapter02_a7.testMethod();
	}
}


class Chapter02_A7_02 extends Thread {

	private Chapter02_A7 chapter02_a7;

	public Chapter02_A7_02(Chapter02_A7 chapter02_a7) {

		this.chapter02_a7 = chapter02_a7;
	}


	@Override
	public void run() {
		chapter02_a7.testMethod();
	}
}


class Chapter02_A7_03 {


	public static void main(String[] args) {


		/**
		 * 从结果验证来，如果一个线程持有对象锁&&该线程发生异常，书上说的是会抛出异常
		 * 但是实际测试中并没有释放锁？这里的原因给抛异常的Integer.parseInt()使用了try catch所以才看到的是同步的效果，并没有释放锁
		 *
		 *
		 * 当线程持有实例对象的锁之后，当前线程抛出异常时，会释放锁
		 *
		 * result:
		 * ThreadName = ThreadA: begin = 1665916508447
		 * ThreadName = ThreadA: exception = 1665916508479
		 * Exception in thread "ThreadA" java.lang.NumberFormatException: For input string: "a"
		 * 	at java.base/java.lang.NumberFormatException.forInputString(NumberFormatException.java:68)
		 * 	at java.base/java.lang.Integer.parseInt(Integer.java:658)
		 * 	at java.base/java.lang.Integer.parseInt(Integer.java:776)
		 * 	at chapter02.Chapter02_A7.testMethod(Chapter02_A7.java:16)
		 * 	at chapter02.Chapter02_A7_01.run(Chapter02_A7.java:42)
		 * ThreadName B = ThreadB:1665916508947
		 * ThreadName: ThreadB : 10
		 * ThreadName: ThreadB : 9
		 * ThreadName: ThreadB : 8
		 * ThreadName: ThreadB : 7
		 * ThreadName: ThreadB : 6
		 * ThreadName: ThreadB : 5
		 * ThreadName: ThreadB : 4
		 * ThreadName: ThreadB : 3
		 * ThreadName: ThreadB : 2
		 * ThreadName: ThreadB : 1
		 */

		try {
			Chapter02_A7 chapter02_a7 = new Chapter02_A7();

			Chapter02_A7_01 chapter02_a7_01 = new Chapter02_A7_01(chapter02_a7);
			chapter02_a7_01.setName("ThreadA");
			chapter02_a7_01.start();

			Thread.sleep(500);
			Chapter02_A7_02 chapter02_a7_02 = new Chapter02_A7_02(chapter02_a7);
			chapter02_a7_02.setName("ThreadB");
			chapter02_a7_02.start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}




