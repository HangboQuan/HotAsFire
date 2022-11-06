package practice;

/**
 * @author quanhangbo
 * @date 22-11-6 下午4:37
 */
public class Demo01_0A extends Thread{

	/**
	 * 写出一个会发生线程安全问题的code
	 *
	 * 注：如果给count变量使用volatile（并且去掉synchronized锁），仍然不能保证每次都会加到20000，
	 * 原因是volatile只能保证可见性，并不能保证原子性
	 *
	 * 可以使用synchronized关键字或者使用Atomic-开头的原子类
	 */

	private int count;

	synchronized public void add() {
		for(int i = 0; i < 10000; i ++ ) {
			this.count ++;
		}
	}

	@Override
	public void run() {
		add();
	}

	public static void main(String[] args) {
		Demo01_0A demo01_01 = new Demo01_0A();

		Thread threadA = new Thread(demo01_01);
		threadA.setName("ThreadA");
		threadA.start();

		Thread threadB = new Thread(demo01_01);
		threadB.setName("ThreadB");
		threadB.start();

		try {
			threadB.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(demo01_01.count);

	}
}

class Demo01_0B {

	/**
	 * 继续写一个和业务案例相关的
	 */
	private String username;
	private String password;


	/**
	 * 不加锁的时候，就是如下结果（非线程安全问题）
	 * a set over
	 * b set over
	 * username = b, password = 456
	 * username = a, password = 456
	 * @param username
	 *
	 * 加上锁之后，如下结果
	 * a set over
	 * username = a, password = 123
	 * b set over
	 * username = b, password = 456
	 */
	public synchronized void addUserInfo(String username) {
		if (username.equals("a")) {
			password = "123";
			System.out.println("a set over");
			try {
				Thread.sleep(2000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			password = "456";
			System.out.println("b set over");
		}

		System.out.println("username = " + username + ", password = " + password);
	}

}

class Demo01_0C extends Thread {

	private Demo01_0B demo01_02;

	public Demo01_0C(Demo01_0B demo01_02) {
		this.demo01_02 = demo01_02;
	}

	@Override
	public void run() {
		demo01_02.addUserInfo("a");
	}
}


class Demo01_0D extends Thread {

	private Demo01_0B demo01_02;

	public Demo01_0D(Demo01_0B demo02) {
		this.demo01_02 = demo02;
	}

	@Override
	public void run() {
		demo01_02.addUserInfo("b");
	}
}

class Demo01_0E {


	public static void main(String[] args) {
		Demo01_0B demo01_02 = new Demo01_0B();

		Demo01_0C demo01_03 = new Demo01_0C(demo01_02);
		demo01_03.setName("ThreadA");
		demo01_03.start();

		Demo01_0D demo01_04 = new Demo01_0D(demo01_02);
		demo01_04.setName("ThreadB");
		demo01_04.start();
	}
}

class Demo01_0F {

	/**
	 * 写一个死锁：
	 * ThreadA先获取了锁 ObjectA, 现在想获取ObjectB
	 * ThreadB先获取了锁 ObjectB, 现在想获取ObjectA
	 * 但是前提是线程并没有释放原有的锁，而都想获取另一把锁，但是另一把锁相互被对方占有不释放，就造成了死锁
	 */
	private Object objectA = new Object();
	private Object objectB = new Object();

	public void serviceMethod() {
		if("ThreadA".equals(Thread.currentThread().getName())) {
			synchronized(objectA) {
				try {
					System.out.println("gain class objectA lock");
					Thread.sleep(2000);

					synchronized (objectB) {
						System.out.println("can I get objectB lock?");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} else {

			synchronized (objectB) {

				try {
					System.out.println("gain class objectB lock");
					Thread.sleep(1000);
					synchronized (objectA) {
						System.out.println("can I get objectA lock?");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Demo01_0G implements Runnable {

	private Demo01_0F demo01_06;

	public Demo01_0G(Demo01_0F demo01_06) {
		this.demo01_06 = demo01_06;
	}

	@Override
	public void run() {
		demo01_06.serviceMethod();
	}
}

class Demo01_0H implements Runnable {
	private Demo01_0F demo01_06;

	public Demo01_0H(Demo01_0F demo01_06) {
		this.demo01_06 = demo01_06;
	}

	@Override
	public void run() {
		demo01_06.serviceMethod();
	}
}

class Demo01_0I {

	/**
	 * result:
	 * gain class objectA lock
	 * gain class objectB lock
	 *
	 * 发生死锁，卡在这里不动了
	 * @param args
	 */
	public static void main(String[] args) {
		Demo01_0F demo01_06 = new Demo01_0F();
		Thread threadA = new Thread(new Demo01_0G(demo01_06));
		threadA.setName("ThreadA");
		threadA.start();

		Thread threadB = new Thread(new Demo01_0H(demo01_06));
		threadB.setName("ThreadB");
		threadB.start();
	}
}

