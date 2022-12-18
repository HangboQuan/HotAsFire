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
> 2.4 wait(long)的使用：带参数的wait(long)等待某一时间内是否有线程对锁进行唤醒，如果超过这个时间则自动唤醒  
> 注意不能通知过早，也即先调用了notify()/notifyAll()，然后再调用wait()，这样wait()就会一直等下去，永久不能被唤醒
3. 生产者和消费者
```java
package chapter03;

import java.util.Date;
import java.util.LinkedList;

/**
 * @author quanhangbo
 * @date 2022/12/6 22:27
 */
public class Chapter03_D9 {
	// 自己实现一个队列，用于实现生产者-消费者模式
	public static void main(String[] args) {
		EventStorge storge = new EventStorge();
		Productor_Thread productor_thread = new Productor_Thread(storge);
		Consumer_Thread consumer_thread = new Consumer_Thread(storge);
		
		productor_thread.start();
		consumer_thread.start();
	}
}

class Consumer_Thread extends Thread {
	private EventStorge eventStorge;
	
	public Consumer_Thread(EventStorge storge) {
		this.eventStorge = storge;
	}

	@Override
	public void run() {
		for(int i = 0; i < 100; i ++ ) {
			eventStorge.take();
		}
	}
}

class Productor_Thread extends Thread {
	private EventStorge eventStorge;
	
	public Productor_Thread(EventStorge storge) {
		this.eventStorge = storge;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 100; i ++ ) {
			eventStorge.put();
		}
	}
}
class EventStorge {
	// 两个属性 最大属性|容器本身
	private int maxSize;
	private LinkedList<Date> storge;
	
	public EventStorge() {
		maxSize = 10;
		storge = new LinkedList<>();
	}
	
	// 提供两个方法， put()生产 take()消费
	
	public synchronized void put() {
		// 1. 队列满了的时候就等待 否则就生产
		while (storge.size() == maxSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		storge.add(new Date());
		System.out.println("生产了" + storge.size() + "个产品");
		// 生产一个产品之后 就通知对方来消费
		notify();
	}
	
	public synchronized void take() {
		// 队列空的时候就等待 否则就消费
		while (storge.size() == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("消费了" + storge.poll() + ", 还剩" + storge.size() + "个产品");
		// 消费一个产品之后，就通知对方生产
		notify();
	}
}

```
4. 通过管道进行线程间的通信：字节流和字符流
> PipedInputStream和PipedOutputStream
> PipedReader和PipedWriter
```java
package chapter03;

import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author quanhangbo
 * @date 22-11-19 下午8:01
 */
public class Chapter03_B8 {

	// 在Java语言中提供了各种各样的输入/输出流Stream, 用于在不同线程之间直接传送数据
	// 一个线程发送数据到输出管道，另一个线程从输入管道中读数据
	// PipedInputStream 和 PipedOutputStream, PipedReader 和 PipedWriter

	public void writeMethod(PipedOutputStream out) {

		try {
			System.out.println("write:");
			for(int i = 0; i < 100; i ++ ) {
				String outData = "" + (i + 1);
				out.write(outData.getBytes());
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B8_01 {

	public void readMethod(PipedInputStream input) {
		try {
			System.out.println("read:");
			byte[] bytes = new byte[20];
			int readLength = input.read(bytes);

			while (readLength != -1) {
				String newData = new String(bytes, 0, readLength);
				System.out.print(newData);
				readLength = input.read(bytes);
			}

			System.out.println();
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B8_02_WriteData extends Thread {

	private Chapter03_B8 chapter03_b8;
	private PipedOutputStream out;

	public Chapter03_B8_02_WriteData(Chapter03_B8 chapter03_b8, PipedOutputStream out) {
		this.chapter03_b8 = chapter03_b8;
		this.out = out;
	}

	@Override
	public void run() {
		chapter03_b8.writeMethod(out);
	}
}


class Chapter03_B8_02_ReadData extends Thread {

	private Chapter03_B8_01 chapter03_b8_01;
	private PipedInputStream input;

	public Chapter03_B8_02_ReadData(Chapter03_B8_01 chapter03_b8_01, PipedInputStream input) {
		this.chapter03_b8_01 = chapter03_b8_01;
		this.input = input;
	}

	@Override
	public void run() {
		chapter03_b8_01.readMethod(input);
	}
}

class Chapter03_B8_03 {

	public static void main(String[] args) {


		/**
		 * 先写后读：
		 * write:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 * read:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 *
		 *
		 * 先读后写(先读的时候，当时没有数据写入，线程阻塞在input.read(bytes), 知道有数据被写入，才继续向下运行)：
		 * read:
		 * write:
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
		 */
		try {
			Chapter03_B8 chapter03_b8 = new Chapter03_B8();
			Chapter03_B8_01 chapter03_b8_01 = new Chapter03_B8_01();

			PipedInputStream inputStream = new PipedInputStream();
			PipedOutputStream outputStream = new PipedOutputStream();


			// 作用是：使两个Stream之间产生通信链接，这样才可以将数据进行输入和输出
			outputStream.connect(inputStream);


			Chapter03_B8_02_ReadData chapter03_b8_02_readData = new Chapter03_B8_02_ReadData(chapter03_b8_01, inputStream);
			chapter03_b8_02_readData.start();

			Thread.sleep(2000);
			Chapter03_B8_02_WriteData chapter03_b8_02_writeData = new Chapter03_B8_02_WriteData(chapter03_b8, outputStream);
			chapter03_b8_02_writeData.start();



		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
```
```java
package chapter03;

import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author quanhangbo
 * @date 22-11-20 下午7:55
 */
public class Chapter03_B9 {

	public void writeMethod(PipedWriter out) {
		try {
			System.out.println("write:");
			for(int i = 0; i < 100; i ++ ) {
				String outData = "" + (i + 1);
				out.write(outData);
				System.out.print(outData);
			}
			System.out.println();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B9_01 {

	public void readMethod(PipedReader reader) {
		try {
			System.out.println("read ");
			char[] charArray = new char[20];
			int readLength = reader.read(charArray);
			while (readLength != -1) {
				String newData = new String(charArray, 0, readLength);
				System.out.print(newData);
				readLength = reader.read(charArray);
			}
			System.out.println();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_B9_02 extends Thread {

	private Chapter03_B9 chapter03_b9;
	private PipedWriter out;

	public Chapter03_B9_02(Chapter03_B9 chapter03_b9, PipedWriter out) {
		this.chapter03_b9 = chapter03_b9;
		this.out = out;
	}

	@Override
	public void run() {
		chapter03_b9.writeMethod(out);
	}
}


class Chapter03_B9_03 extends Thread {
	private Chapter03_B9_01 chapter03_b9_01;
	private PipedReader reader;

	public Chapter03_B9_03(Chapter03_B9_01 chapter03_b9_01, PipedReader reader) {
		this.chapter03_b9_01 = chapter03_b9_01;
		this.reader = reader;
	}

	@Override
	public void run() {
		chapter03_b9_01.readMethod(reader);
	}
}


class Chapter03_B9_04 {


	/**
	 * 以字符串的方式 管道进行线程间通信
	 * write:
	 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
	 * read
	 * 123456789101112131415161718192021222324252627282930313233343536373839404142434445464748495051525354555657585960616263646566676869707172737475767778798081828384858687888990919293949596979899100
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_B9 chapter03_b9 = new Chapter03_B9();
			Chapter03_B9_01 chapter03_b9_01 = new Chapter03_B9_01();

			PipedWriter writer = new PipedWriter();
			PipedReader reader = new PipedReader();

			writer.connect(reader);

			Chapter03_B9_02 chapter03_b9_02 = new Chapter03_B9_02(chapter03_b9, writer);
			Chapter03_B9_03 chapter03_b9_03 = new Chapter03_B9_03(chapter03_b9_01, reader);

			chapter03_b9_02.start();
			Thread.sleep(1000);
			chapter03_b9_03.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
```
5. join()
> 很多情况下主线程创建并启动了子线程，如果子线程中要进行大量耗时运算，主线程往往早于子线程结束之前结束。要使子线程执行完之后主线程才能结束，这时就需要用到join()，join()的作用是等待线程对象销毁
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/24 22:18
 */
public class Chapter03_C2 extends Thread {
	// join() 作用是等待线程对象销毁
	
	@Override
	public void run() {
		try {
			int secondValue = (int) (Math.random() * 10000);
			System.out.println(secondValue);
			Thread.sleep(secondValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class Chapter03_C2_01 {

	/**
	 * 我想要执行在main执行完之前，执行run
	 * 8959
	 * @param args
	 */
	public static void main(String[] args) {
		Chapter03_C2 chapter03_c2 = new Chapter03_C2();
		chapter03_c2.start();
		// 这里并不知道子线程休息了多长时间? Thread.sleep(?)
		System.out.println("我想要执行在main执行完之前，执行run");
	}
}

class Chapter03_C2_02 {

	/**
	 * join()使所属线程对象x正常执行run()的任务 从而使当前线程阻塞 等待x线程销毁后在继续执行当前线程的代码
	 * join()具有使线程排队运行的作用 类似同步的运行效果， join()和synchronized的区别是：join()使用wait()进行等待，synchronize使用对象监视器
	 * 原理作为同步
	 * @param args
	 */
	public static void main(String[] args) {
		
		/**
		 * 6764
		 * 等子线程运行完，main线程结束
		 */
		try {
			Chapter03_C2 chapter03_c2 = new Chapter03_C2();
			chapter03_c2.start();
			
			chapter03_c2.join();
			System.out.println("等子线程运行完，main线程结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
```
5.1 join()遇到interrupt()
> join()本质调用的是join(0) -> wait(0)，所以和wait()一样，遇到interrupte()就会抛出InterruptedException异常
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-26 下午8:08
 */
public class Chapter03_C3 extends Thread {
	
	@Override
	public void run() {
		int i = 0;
		for(; i < 200000000; i ++ ) {
			String newString = new String();
			Math.random();
		}
		// 及时join()遇到中断异常抛出了异常，但是仍然不影响调用join()方法的线程，该线程仍然会执行完run()
		System.out.println(i);
	}
}

class Chapter03_C3_01 extends Thread {

	Chapter03_C3 chapter03_c3;

	public Chapter03_C3_01(Chapter03_C3 chapter03_c3) {
		this.chapter03_c3 = chapter03_c3;
	}

	@Override
	public void run() {
		try {
			chapter03_c3.start();
			chapter03_c3.join();
			//	@Override
//	public final void join() throws InterruptedException {
//		join(0);
//	}
//
//	@Override
//	public void join(final long mills) throws InterruptedException {
//		if (mills > 0) {
//			// 当前线程如果存活
//			if (isAlive()) {
//				// 当前的纳秒
//				final long startTime = System.nanoTime();
//				long delay = mills;
//				do {
//					wait(delay);
//					// 只有当线程存活 && 等待时间不足mills时 才会重新执行wait(delay)
//					// delay = (mills - TimeUnit.NANOSECONDS.toMills(System.nanoTime() - startTime)) > 0
//					// 所以这里为什么要用while循环呢？ 理论上只有wait(delay)就可以了？ 难道是因为wait(delay)不准确吗？
//				} while (isAlive() && (delay = mills - TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startTime)) > 0);
//			}
//		}
//	}
			System.out.println("等待chapter03_c3执行完,然后再打印该行");
		} catch (Exception e) {
			System.out.println("join 遇见 interrupt, 抛出异常");
			e.printStackTrace();
		}
	}
}

class Chapter03_C3_02 extends Thread {

	Chapter03_C3_01 chapter03_c3_01;

	public Chapter03_C3_02(Chapter03_C3_01 chapter03_c3_01) {
		this.chapter03_c3_01 = chapter03_c3_01;
	}

	@Override
	public void run(){
		chapter03_c3_01.interrupt();
	}
	
}


class Chapter03_C3_03 {

	/**
	 * join 遇见 interrupt, 抛出异常
	 * java.lang.InterruptedException
	 * 	at java.base/java.lang.Object.wait(Native Method)
	 * 	at java.base/java.lang.Thread.join(Thread.java:1308)
	 * 	at java.base/java.lang.Thread.join(Thread.java:1375)
	 * 	at chapter03.Chapter03_C3_01.run(Chapter03_C3.java:30)
	 *
	 * 	虽然抛出了异常，但是在ide中看到是红色按钮，chapter03_c3线程仍然不受影响继续执行
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_C3 chapter03_c3 = new Chapter03_C3();
			Chapter03_C3_01 chapter03_c3_01 = new Chapter03_C3_01(chapter03_c3);
			chapter03_c3_01.start();

			Thread.sleep(500);

			Chapter03_C3_02 chapter03_c3_02 = new Chapter03_C3_02(chapter03_c3_01);
			chapter03_c3_02.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
```
> sleep(delay) 和 join(delay)的区别类似于和 wait(delay)的区别，join(delay)和wait(delay)会释放锁，但是sleep(delay)不会释放锁
6. ThreadLocal的用法和注意事项
> ThreadLocal主要解决的是每个线程都绑定自己的值，主要的适用场景有两个
> 1.多线程下的公共工具类，如SimpleFormatDate，Random
> 2.作为全局变量，保证线程隔离性的同时，可以避免层层传参
```java
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
		// 开启1000个线程，改工具类并不会发生线程安全问题，原因是并不是共享对象，而是每次都去主动创建对象SimpleDateFormat
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
		// 开启1000个线程，改工具类并不会发生线程安全问题，原因是并不是共享对象，而是每次都去主动创建对象SimpleDateFormat
		
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
```
```java
package chapter03;

import lombok.Data;

/**
 * @author quanhangbo
 * @date 2022/12/2 8:16
 */
public class Chapter03_D4 {
	// ThreadLocal 第二个主要的用法就是作为全局变量用于传参

	// 举例：传参User对象 一种类命名的方法：UserContextHolder 上下文持有者
	// 在多线程中其实可以声明一个static userMap来使其他类来使用该map 但是在多线程环境下要么加synchronized()来实现同步，要么使用ConcurrentHashMap等线程安全的类
	// 来实现，影响效率；更好的办法是使用ThreadLocal，这样不影响执行效率，也无需层层传递参数，就可保存当前线程对应的用户信息的目的
	public static void main(String[] args) {
		User user = new User("quanhangbo");
		Chapter03_D4_ThreadLocal.userThreadLocal.set(user);
		new Chapter03_D4_01().process();
	}
}

class Chapter03_D4_01 {
	public void process() {
		new Chapter03_D4_02().process();
	}
}

class Chapter03_D4_02 {
	public void process() {
		System.out.println("service 1 " + Chapter03_D4_ThreadLocal.userThreadLocal.get().getName());
		new Chapter03_D4_03().process();
	}
}

class Chapter03_D4_03 {
	public void process() {
		System.out.println("service 2 " + Chapter03_D4_ThreadLocal.userThreadLocal.get().getName());
	}
}

class Chapter03_D4_ThreadLocal {
	public static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();
}

@Data
class User {
	private String name;
	
	public User(String name) {
		this.name = name;
	}
}
```
> ThreadLocal的注意事项
> StrongReference: 强引用 在代码中普遍存在 Object obj = new Object()这类引用，只要强引用存在，GC就不会回收被强引用引用的对象  
> SoftReference: 软引用 用来描述一些还有用但是非必需的对象，只有在发生内存溢出异常之前，将会把这部分对象列入范围之中进行回收，如果这次回收了还是没有足够的内存，才会抛出内存溢出异常  
> WeakReference: 弱引用 用来描述非必需的对象，比SoftReference更弱一些，当GC开始工作时，无论内存是否足够，都会被回收掉被弱引用关联的对象  
> Phantom Reference: 虚引用 最弱的引用关系，一个对象是否有虚引用的存在，不会对其生存时间构成影响，也无法通过虚引用获取一个对象实例，设置虚引用的目的就是能在这个对象被GC回收时收到一个系统通知  

ThreadLocal的原理图 ![](https://github.com/HangboQuan/HotAsFire/blob/main/images/javaconcurrent/ThreadLocal.jpg)
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 2022/12/3 21:54
 */
public class Chapter03_D5<T> {

	/**
	 * ThreadLocal的好处：
	 * 1. 线程安全
	 * 2. 不需要加锁，提高执行效率
	 * 3. 更高效的利用内存、节省开销
	 * 4. 避免传参的麻烦，代码耦合度更低·
	 */
	
	// Thread ThreadLocal ThreadLocalMap
	// 每个Thread对象中都持有一个ThreadLocalMap对象 ThreadLocalMap中的key就是很多ThreadLocal

	/**
	 * 主要方法：
	 * 1.initialValue() 返回初始值 只调用一次initialValue, 如果不重写返回的为null
	 * 2.set() 如果在get()之前执行了set(),就直接返回set的值，不用调initialValue, 为线程设置一个新值
	 * 3.get() 得到这个线程对应的value
	 * 4.remove() 删除对应这个线程的值
	 */
	
	
//	public void set(T value) {
//		Thread t = Thread.currentThread();
//		ThreadLocalMap map = getMap(t);
//		if (map != null) {
//			map.set(this, value);
//		} else {
//			createMap(t, value);
//		}
//	}
//
//	void createMap(Thread t, T firstValue) {
//		t.threadLocals = new ThreadLocalMap(this, firstValue);
//	}
//	public T get() {
//		// 获取当前线程
//		Thread t = Thread.currentThread();
//		ThreadLocalMap map = getMap(t);
//		if (map != null) {
//			// 获取一个Entry对象，此处的this指的是key->ThreadLocal, 如果匹配的话 获取到了e -> 即ThreadLocal对应的set的value
//			ThreadLocalMap.Entry e = map.getEntry(this);
//			if (e != null) {
//				T result = (T) e.value;
//				return result;
//			}
//		}
//		return setInitialValue();
//	}
//
//	public void remove() {
//		ThreadLocalMap m = getMap(Thread.currentThread());
//		if (m != null) {
//			// 删除key为ThreadLocal
//			m.remove(this);
//		}
//	}
//
//	// ThreadLocal.ThreadLocalMap threadLocals = null; ThreadLocalMap是Thread的成员变量 相当于Thread.threadLocals
//	// 是ThreadLocal的静态内部类
//	// ThreadLocalMap 是每个线程Thread的成员变量，其中主要的是键值对Entry[] table, 可以理解为map
//	// key：当前ThreadLocal value: 当前Object对象(也即set(value)的value)
//	// ThreadLoaclMap解决hash冲突的方法是 线性探测法，如果已存在，就去找下一个空位
//	Chapter03_D5.ThreadLocalMap getMap(Thread t) {
//		return t.threadLocals;
//	}
//
//	static class ThreadLocalMap<T> {
//		// Entry 继承了弱引用 在GC中如果一个对象仅仅被weak Reference指向，而没有其他strong Reference
//		// 指向，那么GC运行时就直接回收该对象
//
//		private static final int INITIAL_CAPACITY = 16;
//
//		private ThreadLocalMap.Entry[] table;
//
//		private int size = 0;
//
//		private int threshold;
//		// Entry 其实类似于map, key是ThreadLocal对象，value是Object对象
//		static class Entry extends WeakReference<ThreadLocal<?>> {
//			Object value;
//
//			public Entry(ThreadLocal<?> k, Object v) {
//				super(k);
//				this.value = v;
//			}
//		}
//
//		public ThreadLocalMap() {
//			//
//		}
//
//		public ThreadLocalMap(ThreadLocalMap<?> firstKey, Object firstValue) {
//			//
//		}
//
//		private Entry getEntry(ThreadLocal<?> key) {
//			int i = key.threadLocalHashCode & (table.length - 1);
//			Entry e = table[i];
//			if (e != null && e.get() == key) {
//				return e;
//			} else {
//				return getEntryAfterMiss(key, i, e);
//			}
//		}
// 	}
//
	
	public static void main(String[] args) {
		ThreadLocal threadLocal = new ThreadLocal();
		Thread t = new Thread();
	}

	/**
	 * 使用ThreadLocal的注意事项
	 * 1.内存泄露：某个对象不再用，但是却无法被回收，从而会导致OOM
	 *
	 * 在ThreadLocalMap中的Entry对象，其中key是ThreadLocal是弱引用，value是强引用
	 * 正常情况下：一个线程执行结束之后，GC会回收key，value释放内存
	 * 但是在线程长时间运行的时候(线程池复用线程的时候)
	 * Thread -> ThreadLocalMap -> Entry(key -> null, value -> object)
	 * 在线程长时间引用的时候，由于ThreadLocal是弱引用，就可能被GC回收掉，而他对应的value强引用就无法被回收
	 * 从而导致内存泄露
	 *
	 * 在set(), remove(), rehash()等方法中，可以看到会主动的讲Entry的e.value = null to help the GC
	 * 但是总有一些情况是调用完ThreadLocal之后，就不再调用上述的set(), remove(), rehash()等方法，这样就
	 * 有内存泄露的风险
	 *
	 * 所以强行规定 在调用ThreadLocal之后，必须调用remove()，避免内存泄露
	 */
}
```
6.1 ThreadLocal可能导致的空指针异常
```java
package chapter03;

import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.web.context.request.RequestContextHolder;

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

```
> 使用InheritableThreadLocal类可以让子线程从父线程中获取到值，InneritableThreadLocal是继承于ThreadLocal  
> ThreadLocal很多使用场景中，都是需要对其进行再封装  
7. 经典面试问题
> 7.1 一个线程打印奇数，另一个线程打印偶数
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 2022/12/4 22:48
 */
public class Chapter03_D7 {

// 使用wait()/notify()实现 用两个线程交替打印0~100的奇偶数
// A线程打印奇数，B线程打印偶数

private static volatile int count = 1;
private static Object object = new Object();

public static void main(String[] args) {
	ThreadA threadA = new ThreadA();
	ThreadB threadB = new ThreadB();
	
	threadA.start();
	try {
		Thread.sleep(200);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	threadB.start();
}

// 打印奇数
static class ThreadA extends Thread {
	@Override
	public void run() {
		synchronized (object) {
			for (int i = 1; i <= 50; i++) {
				if (count % 2 == 1) {
					// 如果是偶数
					try {
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 重新获得锁
					System.out.println(Thread.currentThread().getName() + "------" + count);
					count++;
					object.notify();
				}
			}
			
		}
	}
}

static class ThreadB extends Thread {
	@Override
	public void run() {
		synchronized (object) {
			for (int i = 1; i <= 50; i++) {
				// 如果是奇数
				if (count % 2 == 0) {
					try {
						object.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 重新获得锁
				System.out.println(Thread.currentThread().getName() + "------" + count);
				count++;
				object.notify();
			}
			
		}
	}
	
	
}

/**
 * 下面4种情况发生了 Thread才能被唤醒
 * 1. 另一个线程调用这个对象的notify()且刚好唤醒的是本线程
 * 2. 另一个线程调用这个对象的notifyAll()
 * 3. 过了wait(long timeout)的规定时间之后
 * 4. 线程自身调用了interrupt()
 */
//	private static Object object = new Object();
static class Thread1 extends Thread {
	
	@Override
	public void run() {
		synchronized (object) {
			System.out.println(Thread.currentThread().getName() + "开始执行了");
			try {
				object.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "重新获取了锁");
		}
	}
}


static class Thread2 extends Thread {
	
	@Override
	public void run() {
		synchronized (object) {
			try {
				// 这里也验证了wait()释放锁了
				object.notify();
				System.out.println(Thread.currentThread().getName() + "调用了notify()");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}

//	public static void main(String[] args) {
//
//		/**
//		 * Thread-0开始执行了
//		 * Thread-1调用了notify()
//		 * Thread-0重新获取了锁
//		 */
//		Thread1 thread1 = new Thread1();
//		Thread2 thread2 = new Thread2();
//
//		thread1.start();
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		thread2.start();
//	}
}

class Chapter03_D7_01 {
	private static int count = 0;
	private static Object lock = new Object();
	
	// 这种方法虽然能实现该功能，但是假如一个线程连续多次抢占到锁之后，并没执行任何有意义的代码后就释放锁了
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (count < 100) {
					synchronized (lock) {
						// 奇数
						if ((count & 1) == 0) {
							System.out.println(Thread.currentThread().getName() + " " + count++);
						}
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (count < 100) {
					synchronized (lock) {
						// 偶数
						if ((count & 1) == 1) {
							System.out.println(Thread.currentThread().getName() + " " + count++);
						}
					}
				}
			}
		}).start();
	}
}

class Chapter03_D7_02 {
	private static int count = 0;
	private static Object lock = new Object();

	public static void main(String[] args) {
		Thread_Print run = new Thread_Print();
		Thread threadA = new Thread(run);
		Thread threadB = new Thread(run);
		
		threadA.start();
		threadB.start();
		
	}
	static class Thread_Print implements Runnable{
		@Override
		public void run() {
			// 抢占到锁就直接打印
			// 打印之后，自己休眠并唤醒别人
			while (count <= 100) {
				synchronized (lock) {
					System.out.println(Thread.currentThread().getName() + " " + count ++);
					lock.notify();
					if (count <= 100) {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			}
		}
	}
}
```
> 7.2 3个线程轮流打印ABC|3个线程分别打印1~25,26~50,51~75...
```java
package chapter03;

/**
 * @author quanhangbo
 * @date 22-12-6 上午9:31
 */
public class Chapter03_D8 extends Thread {

	/**
	 * 开启3个线程，A线程打印A,B线程打印B,C线程打印C
	 * 打印顺序为A B C A B C A B C
	 * 每个线程打印10次
	 */

	private volatile int flag = 0;
	private static volatile int count = 0;
	private static final int MAX_VALUE = 30;
	private String printValue;

	public Chapter03_D8(String printValue, int flag) {
		this.printValue = printValue;
		this.flag = flag;
	}


	@Override
	public void run() {
		synchronized (this) {
			while (count < MAX_VALUE) {
				if (count % 3 == flag) {
					System.out.println(Thread.currentThread().getName() + " " + this.printValue);
					count ++;
					try {
						sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}


	public static void main(String[] args) {
		new Chapter03_D8("A", 0).start();
		new Chapter03_D8("B", 1).start();
		new Chapter03_D8("C", 2).start();
	}
}

class Chapter03_D8_01 extends Thread {

	// 开启4个线程 A线程打印1~25 B线程打印26~50 C线程打印51~75 D线程打印76~100
	// 最后累计到100, 也就是每个打印1次

	// 问题1 这里count和flag 如果全部加volatile可以实现该功能，如果不加volatile也可以实现，是事实还是巧合 （是事实）
	// 问题2 第一个线程进入while之后，由于while一直无法结束，其他线程是怎么抢占到锁，进而进入方法，实现打印? （是能够结束的，每个线程都有一个count属性，也都是从0开始的累加的）

	private int count = 0;
	private int flag;
	private static final int MAX_VALUE = 100;
	private Object object = new Object();


	public Chapter03_D8_01(int flag) {
		this.flag = flag;
	}
	
	
	@Override
	public void run() {
		synchronized (object) {
			// 因此每个线程都会一个count值, 是和其他线程隔离的，原因是不同的对象也不可能出现共享，自然也就不可能有线程安全问题
			while (count < MAX_VALUE) {
				if ((count / 25) % 4 == flag) {
					System.out.println(Thread.currentThread().getName() + " " + (count + 1));
				}
				count ++;
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// 这里并没有共享对象,每创建一个对象就有一个对应count,flag
		new Chapter03_D8_01(0).start();
		new Chapter03_D8_01(1).start();
		new Chapter03_D8_01(2).start();
		new Chapter03_D8_01(3).start();
	}
}
```
8. 为什么wait()/notify()/notifyAll()是Object的方法，而sleep()/join()是Thread类的方法？
> wait()/notify()/notifyAll()是要在同步锁中才能使用，否则就会报异常。Java提供的锁是对象级的而不是线程级的，每个对象头中都有个标记位来标记锁，在线程中是可以竞争到这个对象，因此线程等待某些锁就直接调用对象层面的wait()/notify()/notifyAll()就行, wait()如果定义在Thread的话，等待哪个锁就不明确了, wait()/notify()/notifyAll()是锁(对象)级别的操作，并不是线程级别的操作，所以是被定义在Object类中    

> sleep()的作用是让线程在预期的时间内执行，其他时候不要来占用CPU资源，sleep()是属于线程级别的，它是让线程在限定的时间后去执行      
