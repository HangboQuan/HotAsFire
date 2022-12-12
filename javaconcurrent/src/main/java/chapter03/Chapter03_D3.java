package chapter03;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author quanhangbo
 * @date 2022/12/1 21:49
 */
public class Chapter03_D3 {
	/**
	 * ThreadLocal的两大使用场景：
	 *  1.解决工具类的线程安全问题，如SimpleFormatDate, Random
	 *  2.作为全局变量，减少了方法之间冗余的传参
	 */
	
	// 1. 使用2个线程 分别打印格式化之后的日期

	/**
	 * 1970-01-01 08:01:40
	 * 1970-01-01 08:00:10
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(new Chapter03_D3().dateParse(100));
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(new Chapter03_D3().dateParse(10));
			}
		}).start();
	}
	public String dateParse(int seconds) {
		Date date = new Date(1000 * seconds);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return df.format(date);
	}
}

class Chapter03_D3_01 {

	public static void main(String[] args) throws Exception {
		// 开启1000个线程，该工具类并不会发生线程安全问题，原因是并不是共享对象，而是每次都去主动创建对象SimpleDateFormat
		for(int i = 0; i < 1000; i ++ ) {
			int finalI = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(new Chapter03_D3_01().dateParse(finalI));
				}
			}).start();
			Thread.sleep(100);
		}
	}
	public String dateParse(int seconds) {
		Date date = new Date(1000 * seconds);
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		return df.format(date);
	}
}

class Chapter03_D3_02 {

	static SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	public static void main(String[] args) throws Exception {
		/**
		 * 这里看出就由于共享了 df对象 就发生线程非安全问题
		 * 1970-01-01 08:16:24
		 * 1970-01-01 08:16:14
		 * 1970-01-01 08:14:21
		 * 1970-01-01 08:16:24
		 * 1970-01-01 08:16:24
		 */
		for(int i = 0; i < 1000; i ++ ) {
			int finalI = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(new Chapter03_D3_02().dateParse(finalI));
				}
			}).start();
		}
	}
	public String dateParse(int seconds) {
		Date date = new Date(1000 * seconds);
		return df.format(date);
	}
}


class Chapter03_D3_03 {

	static SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
	// 使用线程池 降低创建1000个线程以及销毁带来的性能的消耗 -> 线程池  但是仍然需要需要借助synchronized()加锁来实现同步
	public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	public static void main(String[] args) throws Exception {
		// 开启1000个线程，改工具类并不会发生线程安全问题，原因是并不是共享对象，而是每次都去主动创建对象SimpleDateFormat
		
		/**
		 * 这里看出就由于共享了 df对象 就发生线程非安全问题
		 1970-01-01 08:10:20
		 1970-01-01 08:10:22
		 1970-01-01 08:10:19
		 1970-01-01 08:10:18
		 1970-01-01 08:10:18
		 */
		for(int i = 0; i < 1000; i ++ ) {
			int finalI = i;
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(new Chapter03_D3_03().dateParse(finalI));
				}
			});
		}
		threadPool.shutdown();
	}
	
	
	// 虽然加的是类锁，使锁排队执行，1000个线程排队仍然会造成性能消耗
	public String dateParse(int seconds) {
		Date date = new Date(1000 * seconds);
		String sdf = null;
		synchronized (Chapter03_D3_03.class) {
			sdf = df.format(date);
		}
		return sdf;
	}
}


class Chapter03_D3_ThreadLocal extends ThreadLocal {
	public static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
		}
	};
	
}
class Chapter03_D3_04 {

	// threadLocal的用途 不使用synchronized()，使用线程副本同样保证了线程安全
	public static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	public static void main(String[] args) throws Exception {
		for(int i = 0; i < 1000; i ++ ) {
			int finalI = i;
			threadPool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println(new Chapter03_D3_04().dateParse(finalI));
				}
			});
		}
		threadPool.shutdown();
	}

	public String dateParse(int seconds) {
		Date date = new Date(1000 * seconds);
		return Chapter03_D3_ThreadLocal.threadLocal.get().format(date);
	}
	
}