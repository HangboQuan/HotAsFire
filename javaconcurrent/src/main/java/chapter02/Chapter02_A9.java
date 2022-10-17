package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 上午8:57
 */
public class Chapter02_A9 {

	private String getData1;
	private String getData2;

	synchronized public void doLongTimeTask() {
		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			getData1 = "长时间处理任务后从远程返回的值1 threadName = " + Thread.currentThread().getName();
			getData2 = "长时间处理任务后从远程返回的值2 threadName = " + Thread.currentThread().getName();

			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_A9_01 {

	public static long beginTime1;
	public static long endTime1;
	public static long beginTime2;
	public static long endTime2;
}

class Chapter02_A9_02 extends Thread {

	private Chapter02_A9 chapter02_a9;

	public Chapter02_A9_02(Chapter02_A9 chapter02_a9) {
		this.chapter02_a9 = chapter02_a9;
	}

	@Override
	public void run(){
		super.run();
		Chapter02_A9_01.beginTime1 = System.currentTimeMillis();
		chapter02_a9.doLongTimeTask();
		Chapter02_A9_01.endTime1 = System.currentTimeMillis();
	}
}

class Chapter02_A9_03 extends Thread {

	private Chapter02_A9 chapter02_a9;

	public Chapter02_A9_03(Chapter02_A9 chapter02_a9) {
		this.chapter02_a9 = chapter02_a9;
	}

	@Override
	public void run(){
		super.run();
		Chapter02_A9_01.beginTime2 = System.currentTimeMillis();
		chapter02_a9.doLongTimeTask();
		Chapter02_A9_01.endTime2 = System.currentTimeMillis();
	}
}

class Chapter02_A9_04 {

	public static void main(String[] args) {

		/**
		 * synchronized锁同步方法的时候,范围面大一些,如果A线程调用同步方法执行一个长时间的任务,B线程则必须等待较长的时间;
		 *
		 * 那么就可以使用同步块来解决这样的问题
		 * result:
		 * begin task
		 * 长时间处理任务后从远程返回的值1 threadName = Thread-0
		 * 长时间处理任务后从远程返回的值2 threadName = Thread-0
		 * end task
		 * begin task
		 * 长时间处理任务后从远程返回的值1 threadName = Thread-1
		 * 长时间处理任务后从远程返回的值2 threadName = Thread-1
		 * end task
		 * 耗时: 6003
		 *
		 */
		Chapter02_A9 chapter02_a9 = new Chapter02_A9();

		Chapter02_A9_02 chapter02_a9_02 = new Chapter02_A9_02(chapter02_a9);
		chapter02_a9_02.start();

		Chapter02_A9_03 chapter02_a9_03 = new Chapter02_A9_03(chapter02_a9);
		chapter02_a9_03.start();

		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long beginTime = Chapter02_A9_01.beginTime1;
		if(Chapter02_A9_01.beginTime2 < Chapter02_A9_01.beginTime1) {
			beginTime = Chapter02_A9_01.beginTime2;
		}

		long endTime = Chapter02_A9_01.endTime1;
		if(Chapter02_A9_01.endTime2 > Chapter02_A9_01.endTime1) {
			endTime = Chapter02_A9_01.endTime2;
		}

		System.out.println("耗时: " + (endTime - beginTime));

	}
}