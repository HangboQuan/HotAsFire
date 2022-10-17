package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-17 下午8:35
 */
public class Chapter02_B5 {

	/**
	 * synchronized同步方法:
	 * 1. 对其他synchronized同步方法或者synchronized(this)同步代码块呈阻塞状态
	 * 2. 同一时间只有一个线程可以执行synchronized同步方法
	 *
	 * synchronized(this)同步方法:
	 * 1. 对其他synchronized同步方法或者synchronized(this)同步代码块呈阻塞状态
	 * 2. 同一时间只有一个线程可以执行synchronized(this)同步方法
	 */

	private String username;
	private String password;

	private String anyString = new String();

	public void setUsernameAndPassword(String uparm, String pparm) {
		/**
		 * 这个锁非this对象和锁this　的效果是一样的，都是同步的打印了信息
		 *
		 * 锁this和锁非this的区别是: 如果一个类中synchronized特别多的话，由于锁住了this,该类中的其他代码就必须是同步执行的,耗时较高，影响效率
		 * 如果synchronized(非this)则该代码块的代码和 其他synchronized(this)或者synchronized(其他对象)同步方法是异步执行的，不与其他同步方法争抢锁，
		 * 因此可以提高执行效率
		 *
		 */
		synchronized (anyString){
			try {
				System.out.println("threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				username = uparm;
				Thread.sleep(2000);
				password = pparm;

				System.out.println("threadName = " + Thread.currentThread().getName() + " username = " + username + " password = " + password +
						System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter02_B5_01 extends Thread {

	private Chapter02_B5 chapter02_b5;

	public Chapter02_B5_01(Chapter02_B5 chapter02_b5) {
		this.chapter02_b5 = chapter02_b5;
	}

	@Override
	public void run() {
		chapter02_b5.setUsernameAndPassword("A", "AA");

	}
}

class Chapter02_B5_02 extends Thread {

	private Chapter02_B5 chapter02_b5;

	public Chapter02_B5_02(Chapter02_B5 chapter02_b5) {
		this.chapter02_b5 = chapter02_b5;
	}

	@Override
	public void run() {
		chapter02_b5.setUsernameAndPassword("B", "BB");

	}
}

class Chapter02_B5_03 {

	/**
	 * result:
	 * threadName = ThreadA 1666010728821
	 * threadName = ThreadA username = A password = AA1666010730822
	 * threadName = ThreadB 1666010730822
	 * threadName = ThreadB username = B password = BB1666010732822
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_B5 chapter02_b5 = new Chapter02_B5();

		Chapter02_B5_01 chapter02_b5_01 = new Chapter02_B5_01(chapter02_b5);
		chapter02_b5_01.setName("ThreadA");
		chapter02_b5_01.start();

		Chapter02_B5_02 chapter02_b5_02 = new Chapter02_B5_02(chapter02_b5);
		chapter02_b5_02.setName("ThreadB");
		chapter02_b5_02.start();
	}

}
