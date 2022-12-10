线程是操作系统独立的个体，个体不经过特殊处理就不能成为一个整体，线程间的通信就是作为整体的必用方案之一，线程间进行通信之后，系统之间的交互性会更加强大，能提高CPU的利用率同时使得开发人员对各线程任务在处理的过程中进行有效的把控和监督。
1. 不使用等待/通知的弊端
```java
public class Chapter03_A0 {

	// 线程间进行通信之后，系统之间的交互性会更加强大，大大提高CPU的利用率还可以是开发者能进行有效的把控和监督

	// sleep()和while(true)来实现线程之间的通信
	private volatile List list = new ArrayList();


	public void add() {
		list.add("Tom");
	}

	public int size() {
		return list.size();
	}
}

class Chapter03_A0_01 extends Thread {

	private Chapter03_A0 chapter03_a0;

	public Chapter03_A0_01(Chapter03_A0 chapter03_a0) {

		this.chapter03_a0 = chapter03_a0;
	}

	// 下面这两个方法的执行效果都是一样的， 最开始的时候我还以为处理异常的位置加的不正确也会导致不可思议的情况 看来是不存在的 可能是idea自身缓存的问题
	@Override
	public void run() {

		/**
		 * ThreadA 添加了第1元素
		 * ThreadA 添加了第2元素
		 * ThreadA 添加了第3元素
		 * ThreadA 添加了第4元素
		 * ThreadA 添加了第5元素
		 * ThreadB, This method has finished
		 * java.lang.InterruptedException
		 * 	at chapter03.Chapter03_A0_02.run(Chapter03_A0.java:94)
		 * ThreadA 添加了第6元素
		 * ThreadA 添加了第7元素
		 * ThreadA 添加了第8元素
		 * ThreadA 添加了第9元素
		 * ThreadA 添加了第10元素
		 */
		for(int i = 0; i < 10; i ++ ) {
			chapter03_a0.add();
			try {
				System.out.println(Thread.currentThread().getName() + " 添加了第" + chapter03_a0.size() + "元素");
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		/**
		 * result:
		 *
		 * ThreadA添加了第1元素
		 * ThreadA添加了第2元素
		 * ThreadA添加了第3元素
		 * ThreadA添加了第4元素
		 * ThreadA添加了第5元素
		 * ThreadB, This method has finished
		 * java.lang.InterruptedException
		 * 	at chapter03.Chapter03_A0_02.run(Chapter03_A0.java:76)
		 * ThreadA添加了第6元素
		 * ThreadA添加了第7元素
		 * ThreadA添加了第8元素
		 * ThreadA添加了第9元素
		 * ThreadA添加了第10元素
		 */
//		try {
//			for(int i = 0; i < 10; i ++ ) {
//				chapter03_a0.add();
//				System.out.println(Thread.currentThread().getName() + "添加了第" + chapter03_a0.size() + "元素");
//				Thread.sleep(1000);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}


class Chapter03_A0_02 extends Thread {
	private Chapter03_A0 chapter03_a0;

	public Chapter03_A0_02(Chapter03_A0 chapter03_a0) {

		this.chapter03_a0 = chapter03_a0;
	}

	@Override
	public void run() {
		/**
		 * ThreadB通过不断轮询的方式来检测某一个条件，会浪费CPU资源
		 *
		 * 如果轮询的间隔更小，则更浪费CPU，应为上下文切换的次数会更加频繁
		 * 如果轮询的间隔更大，则可能会取不到想要得到的数据
		 *
		 * 为了减少浪费CPU的资源浪费，通过wait/notify机制来实现线程之间的通信
		 */
		try {
			while(true) {
				if(chapter03_a0.size() == 5) {
					System.out.println(Thread.currentThread().getName() + ", This method has finished");
					throw new InterruptedException();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


class Chapter03_A0_03 {

	public static void main(String[] args) {
		Chapter03_A0 chapter03_a0 = new Chapter03_A0();


		Chapter03_A0_01 chapter03_a0_01 = new Chapter03_A0_01(chapter03_a0);

		Chapter03_A0_02 chapter03_a0_02 = new Chapter03_A0_02(chapter03_a0);

		chapter03_a0_01.setName("ThreadA");
		chapter03_a0_02.setName("ThreadB");


		chapter03_a0_01.start();
		chapter03_a0_02.start();
	}
}
```
2. wait()/notify()/notifyAll()的使用
> 2.1 wait()/notify()/notifyAll() 都是超类Object的方法，调用这3个方法之前 都必须首先获得该对象的对象级别的锁  
如果没有持有适当的锁，则会抛出IllegalMonitorStateException  
wait(): 在同步块中执行当前代码的线程进行等待，并且会释放锁  
notify(): 在同步块中通知可能等待该对象的对象锁的线程，如果有多个线程等待，则随机通知一个线程; 执行notify()并不会马上释放对象锁，wait()并不会
马上获取锁，要等到notify执行完(退出同步代码块)，当前线程才会释放锁 -> wait()才能获取对象锁  
notifyAll(): 在同步块中通过所有等待该对象的对象锁的线程
调用wait()必须使用notify()/notifyAll()来通知，如果不通知，则该同步代码块会一直阻塞  
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-6 下午7:08
 */
public class Chapter03_A2 extends Thread {

	private Object lock;

	public Chapter03_A2(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
		 	synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " begin  wait time = " + System.currentTimeMillis());
				lock.wait();
				System.out.println(Thread.currentThread().getName() + " end wait time = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter03_A2_01 extends Thread {

	private Object lock;

	public Chapter03_A2_01(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " begin  wait time = " + System.currentTimeMillis());
				lock.notify();
				// 注意： 执行notify()/notifyAll()并不会立即释放锁，而是要将notify()/notifyAll()所在的同步块执行完之后才会释放锁
				System.out.println(Thread.currentThread().getName() + " end wait time = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter03_A2_02 {

	/**
	 * 从这里的耗时就可以看出: 2s后线程被notify通知唤醒
	 * result:
	 * ThreadA begin  wait time = 1667783197064
	 * ThreadB begin  wait time = 1667783199065
	 * ThreadB end wait time = 1667783199065
	 * ThreadA end wait time = 1667783199066
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Object lock = new Object();

			Chapter03_A2 chapter03_a2 = new Chapter03_A2(lock);
			chapter03_a2.setName("ThreadA");
			chapter03_a2.start();

			Thread.sleep(2000);

			Chapter03_A2_01 chapter03_a2_01 = new Chapter03_A2_01(lock);
			chapter03_a2_01.setName("ThreadB");
			chapter03_a2_01.start();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
``` 
> 2.2 当线程呈wait()状态时，调用线程对象的interrupt()会出现InterruptedException
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午5:49
 */
public class Chapter03_A6 {

	public void serviceMethod(Object lock) {

		try {
			synchronized (lock) {
				System.out.println("begin exec wait() = " + System.currentTimeMillis());
				lock.wait();
				System.out.println("end exec wait() = " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_A6_01 extends Thread {

	private Chapter03_A6 chapter03_a6;

	public Chapter03_A6_01(Chapter03_A6 chapter03_a6) {
		this.chapter03_a6 = chapter03_a6;
	}


	@Override
	public void run() {
		Object lock = new Object();
		chapter03_a6.serviceMethod(lock);
	}

}


class Chapter03_A6_02 {


	/**
	 * 线程呈等待状态 被线程调用会跑异常InterruptedException
	 * result:
	 * begin exec wait() = 1667901435542
	 * java.lang.InterruptedException
	 * 	at java.base/java.lang.Object.wait(Native Method)
	 * 	at java.base/java.lang.Object.wait(Object.java:326)
	 * 	at chapter03.Chapter03_A6.serviceMethod(Chapter03_A6.java:14)
	 * 	at chapter03.Chapter03_A6_01.run(Chapter03_A6.java:36)
	 *
	 * 执行同步代码块的过程中，遇到线程异常而导致线程终止，锁也会被释放
	 *
	 * @param args
	 */

	public static void main(String[] args) {
		try {
			Chapter03_A6 chapter03_06 = new Chapter03_A6();

			Chapter03_A6_01 chapter03_a6_01 = new Chapter03_A6_01(chapter03_06);
			chapter03_a6_01.start();
			Thread.sleep(5000);
			chapter03_a6_01.interrupt();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}
}
```
> 2.3 notify()只随机唤醒一个等待获取该对象锁的线程，notifyAll()唤醒全部等待获取该对象锁的线程
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-8 下午9:10
 */
public class Chapter03_A7 {

	public void serviceMethod(Object lock) {
		try {
			synchronized (lock) {
				System.out.println("begin wait() ThreadName = " + Thread.currentThread().getName());
				lock.wait();
				System.out.println("end wait() ThreadName = " + Thread.currentThread().getName());
			}
		} catch (Exception e) {

		}
	}
}

class Chapter03_A7_01 extends Thread {

	private Object lock;

	public Chapter03_A7_01(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_02 extends Thread {

	private Object lock;

	public Chapter03_A7_02(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_03 extends Thread {

	private Object lock;

	public Chapter03_A7_03(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		Chapter03_A7 chapter03_a7 = new Chapter03_A7();
		chapter03_a7.serviceMethod(lock);
	}
}


class Chapter03_A7_04 extends Thread {
	private Object lock;

	public Chapter03_A7_04(Object lock) {
		this.lock = lock;
	}

	/**
	 * 如果是多个notify(), 那么所有的wait()等待的方法都会被唤醒
	 *
	 * 这里可以改为notifyAll(), 如果notify()小于wait()的数量，就总会使线程处于等待的状态，从而永远无法得到唤醒
	 */
	@Override
	public void run() {
		synchronized (lock) {
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();
//			lock.notify();

			lock.notifyAll();
		}
	}
}


class Chapter03_A7_05 {

	public static void main(String[] args) {

		/**
		 * 验证了notify()只能随机的唤醒一个线程
		 * result:
		 * begin wait() ThreadName = Thread-0
		 * begin wait() ThreadName = Thread-1
		 * begin wait() ThreadName = Thread-2
		 * end wait() ThreadName = Thread-0
		 */
		try {
			Object obj = new Object();

			Chapter03_A7_01 chapter03_a7_01 = new Chapter03_A7_01(obj);
			Chapter03_A7_02 chapter03_a7_02 = new Chapter03_A7_02(obj);
			Chapter03_A7_03 chapter03_a7_03 = new Chapter03_A7_03(obj);

			chapter03_a7_01.start();
			chapter03_a7_02.start();
			chapter03_a7_03.start();

			Thread.sleep(1000);

			Chapter03_A7_04 chapter03_a7_04 = new Chapter03_A7_04(obj);
			chapter03_a7_04.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
```
