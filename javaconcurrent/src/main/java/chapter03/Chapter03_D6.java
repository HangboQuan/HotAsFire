package chapter03;

/**
 * @author quanhangbo
 * @date 2022/12/3 23:30
 */
public class Chapter03_D6 {
	ThreadLocal<Long> threadLocal = new ThreadLocal<>();
	
	public void set() {
		threadLocal.set(Thread.currentThread().getId());
	}
	
	// 这里的原因是：ThreadLocal的类泛型是Long 并不是 long, 这样先get()获取的是null, 结果还要将Long->long
	// 进行拆箱，所以就报空指针异常了，如果将这里的long -> Long 就不会抛异常了
	public long get() {
		return threadLocal.get();
	}
	// 在Spring中每个Http请求中都对应一个线程，线程之间相互隔离，这就是ThreadLocal典型应用场景
	// DateTimeContextHolder, RequestContextHolder等类中使用了ThreadLocal
	public static void main(String[] args) {
		Chapter03_D6 chapter03_d6 = new Chapter03_D6();
		
		/**
		 * 如果在这里先调用get(), 跑出了异常，而且是在15行
		 * Exception in thread "main" java.lang.NullPointerException
		 * 	at chapter03.Chapter03_D6.get(Chapter03_D6.java:15)
		 * 	at chapter03.Chapter03_D6.main(Chapter03_D6.java:21)
		 */
		chapter03_d6.get();
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				chapter03_d6.set();
				System.out.println(chapter03_d6.get());
			}
		});
		thread.start();
	}
}
