package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 下午7:28
 */
public class Chapter02_B1 {
	private String getData1;
	private String getData2;

	public void doLongTimeTask() {

		try {
			System.out.println("begin task");
			Thread.sleep(3000);
			String privateGetData1 = "长时间处理任务后从远程返回的值1 threadName = " + Thread.currentThread().getName();
			String privateGetData2 = "长时间处理任务后从远程返回的值2 threadName = " + Thread.currentThread().getName();

			/**
			 * 通过使用同步代码块,缩小同步锁的范围,来降低耗时
			 */
			synchronized(this) {
				getData1 = privateGetData1;
				getData2 = privateGetData2;
			}

			System.out.println(getData1);
			System.out.println(getData2);
			System.out.println("end task");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter02_B1_01 extends Thread {


	private Chapter02_B1 chapter02_b1;

	public Chapter02_B1_01(Chapter02_B1 chapter02_b1) {
		this.chapter02_b1 = chapter02_b1;
	}

	@Override
	public void run() {
		Chapter02_B1_04.beginTime1 = System.currentTimeMillis();
		chapter02_b1.doLongTimeTask();
		Chapter02_B1_04.endTime1 = System.currentTimeMillis();
	}
}

class Chapter02_B1_02 extends Thread {


	private Chapter02_B1 chapter02_b1;

	public Chapter02_B1_02(Chapter02_B1 chapter02_b1) {
		this.chapter02_b1 = chapter02_b1;
	}

	@Override
	public void run() {
		Chapter02_B1_04.beginTime2 = System.currentTimeMillis();
		chapter02_b1.doLongTimeTask();
		Chapter02_B1_04.endTime2 = System.currentTimeMillis();
	}
}

class Chapter02_B1_04 {
	public static long beginTime1;
	public static long beginTime2;
	public static long endTime1;
	public static long endTime2;
}

class Chapter02_B1_03 {

	public static void main(String[] args) {

		/**
		 * result:
		 * begin task
		 * begin task
		 * 长时间处理任务后从远程返回的值1 threadName = ThreadA
		 * 长时间处理任务后从远程返回的值2 threadName = ThreadA
		 * end task
		 * 长时间处理任务后从远程返回的值1 threadName = ThreadB
		 * 长时间处理任务后从远程返回的值2 threadName = ThreadB
		 * end task
		 * 耗时: = 3005
		 */
		Chapter02_B1 chapter02_b1 = new Chapter02_B1();

		Chapter02_B1_01 chapter02_b1_01 = new Chapter02_B1_01(chapter02_b1);
		chapter02_b1_01.setName("ThreadA");
		chapter02_b1_01.start();

		Chapter02_B1_02 chapter02_b1_02 = new Chapter02_B1_02(chapter02_b1);
		chapter02_b1_02.setName("ThreadB");
		chapter02_b1_02.start();

		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long beginTime = Chapter02_B1_04.beginTime1;

		if(Chapter02_B1_04.beginTime2 < Chapter02_B1_04.beginTime1) {
			beginTime = Chapter02_B1_04.beginTime2;
		}

		long endTime = Chapter02_B1_04.endTime1;
		if(Chapter02_B1_04.endTime2 > Chapter02_B1_04.endTime1) {
			endTime = Chapter02_B1_04.endTime2;
		}

		System.out.println("耗时: = " + (endTime - beginTime));
	}
}