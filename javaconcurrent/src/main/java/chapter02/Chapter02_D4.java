package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-27 下午6:48
 */
public class Chapter02_D4 extends Thread {


	/**
	 * synchronized和volatile的比较
	 *
	 * 1. 关键字volatile是线程同步的轻量级实现，volatile的性能优于synchronized；volatile只能修饰变量，而synchronized可以修饰方法，以及代码块。
	 * 随着版本的迭代，synchronized关键字在执行效率上大幅提升，开发中也更多使用的是synchronized
	 *
	 * 2. 多线程访问volatile不会发生阻塞，synchronized会发生阻塞
	 *
	 * 3. volatile能保证数据的可见性，但不能保证原子性；synchronized能保证数据的原子性，也可以保证数据的可见性
	 *
	 * 4. volatile是解决变量在多个线程之间的可见性；而synchronized是解决多个线程之间的同步性
	 */
	volatile private boolean isRunning = true;

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {

		this.isRunning = isRunning;
	}


	@Override
	public void run() {
		System.out.println("线程进入run方法了");
		while(isRunning == true) {

		}
		System.out.println("线程被停止了！");

	}

}

class Chapter02_D4_01 {

	/**
	 * private boolean isRunning = true, 存在于公共堆栈及线程的私有栈中, JVM被设置为-server时为了线程运行效率，从私有堆栈中获取的isRunning=true
	 *
	 * chapter02_03.setRunning(false)更新的是 公共堆栈的isRunning=false
	 *
	 * 出现死循环的问题就是私有堆栈的值和公共堆栈的值 不同步造成的
	 *
	 * 解决方法就是需要用到volatile 主要作用是当线程访问isRunning，强制从公共堆栈进行取值
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_D4 chapter02_d4 = new Chapter02_D4();
		chapter02_d4.start();
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		chapter02_d4.setRunning(false);
		System.out.println("已经被赋值为false");
	}

}
