package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-16 下午4:00
 */
public class Chapter02_A5 {

	synchronized public void service1() {
		System.out.println("get into service1");
		service2();
	}

	synchronized public void service2() {
		System.out.println("get into service2");
		service3();
	}

	synchronized public void service3() {
		System.out.println("get into service3");
	}

}

class Chapter02_A5_01 extends Thread {

	private Chapter02_A5 chapter02_a5;

	public Chapter02_A5_01(Chapter02_A5 chapter02_a5) {
		this.chapter02_a5 = chapter02_a5;
	}

	@Override
	public void run() {
		chapter02_a5.service1();
	}

	/**
	 * synchronized是可重入锁，当一个线程得到一个对象锁之后，再次请求对象锁时是可以再次得到该对象的锁
	 *
	 * 可重入锁的概念：每次可以获取自己的内部锁
	 * result:
	 * get into service1
	 * get into service2
	 * get into service3
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter02_A5 chapter02_a5 = new Chapter02_A5();

		Chapter02_A5_01 chapter02_a5_01 = new Chapter02_A5_01(chapter02_a5);
		chapter02_a5_01.start();
	}
}


