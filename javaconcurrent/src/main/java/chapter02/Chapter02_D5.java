package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-27 下午8:13
 */
public class Chapter02_D5 extends Thread {

	/**
	 * volatile非原子特性
	 *
	 * 用volatile修饰的变量保证了变量在多个线程之间的可见性，但是并不能保证原子性
	 * 用volatile修饰变量在内存中的工作流程
	 * 1. read和load: 从主存中复制变量到当前工作内存
	 * 2. use和assign(分配): 执行代码，改变共享变量的值
	 * 3. store和write: 用工作内存数据刷新主存对应变量的值
	 * 其中load use assign这三步操作是非原子性的 当执行read和load从主存加载变量之后，如果此时主存的数据发生更改，但是在线程工作区变量已经加载了，
	 * 无法同步更新，因此造成数据不同步，即出现非线程安全问题
	 *
	 * 使用volatile修饰的变量，JVM保证从主内存加载到线程工作内存的值是最新的，如果线程1和线程2在执行read和load的时候，发现主存count值是10，那么就会
	 * 加载这个最新的值，主要就是解决线程之间的可见性问题，但是不能保证原子性问题
	 */

	volatile public static int count;

	/**
	 * 在这里如果不加synchronized 则无法保证它的原子性，最终的结果就是小于10000
	 * 加上synchronized 则最终的结果是10000
	 */
	synchronized private static void addCount() {

		for(int i = 0; i < 100; i ++ ) {
			count ++;
		}
		System.out.println("ThreadName = " + Thread.currentThread().getName() + " count = " + count);
	}


	@Override
	public void run() {
		addCount();
	}
}



class Chapter02_D5_01 {

	/**
	 * 开启了100个线程，每个线程做得事情是将公有变量+100，按照同步的结果，最终的结果应该是10000
	 *
	 * 但是实际来看最终执行完的结果 总是小于10000
	 * ThreadName = Thread-2 count = 300
	 * ThreadName = Thread-9 count = 900
	 * ThreadName = Thread-3 count = 700
	 * ThreadName = Thread-6 count = 700
	 * ThreadName = Thread-5 count = 500
	 * ThreadName = Thread-4 count = 400
	 * ThreadName = Thread-0 count = 200
	 * ThreadName = Thread-1 count = 300
	 * ThreadName = Thread-7 count = 800
	 * ThreadName = Thread-11 count = 1185
	 * ThreadName = Thread-10 count = 1085
	 * ThreadName = Thread-8 count = 1085
	 * ThreadName = Thread-12 count = 1285
	 * ThreadName = Thread-13 count = 1385
	 * ThreadName = Thread-14 count = 1485
	 * ThreadName = Thread-15 count = 1585
	 * ThreadName = Thread-16 count = 1685
	 * ThreadName = Thread-19 count = 1786
	 * ThreadName = Thread-20 count = 1985
	 * ThreadName = Thread-22 count = 2285
	 * ThreadName = Thread-17 count = 1885
	 * ThreadName = Thread-18 count = 2285
	 * ThreadName = Thread-21 count = 2085
	 * ThreadName = Thread-23 count = 2385
	 * ThreadName = Thread-25 count = 2585
	 * ThreadName = Thread-24 count = 2485
	 * ThreadName = Thread-26 count = 2685
	 * ThreadName = Thread-27 count = 2785
	 * ThreadName = Thread-29 count = 2885
	 * ThreadName = Thread-30 count = 3085
	 * ThreadName = Thread-28 count = 3085
	 * ThreadName = Thread-35 count = 3585
	 * ThreadName = Thread-31 count = 3385
	 * ThreadName = Thread-34 count = 3485
	 * ThreadName = Thread-33 count = 3285
	 * ThreadName = Thread-32 count = 3185
	 * ThreadName = Thread-36 count = 3685
	 * ThreadName = Thread-38 count = 3885
	 * ThreadName = Thread-37 count = 3885
	 * ThreadName = Thread-39 count = 3985
	 * ThreadName = Thread-40 count = 4085
	 * ThreadName = Thread-41 count = 4198
	 * ThreadName = Thread-42 count = 4198
	 * ThreadName = Thread-43 count = 4298
	 * ThreadName = Thread-44 count = 4498
	 * ThreadName = Thread-46 count = 4498
	 * ThreadName = Thread-47 count = 4598
	 * ThreadName = Thread-45 count = 4698
	 * ThreadName = Thread-48 count = 4798
	 * ThreadName = Thread-50 count = 4998
	 * ThreadName = Thread-51 count = 5098
	 * ThreadName = Thread-49 count = 4998
	 * ThreadName = Thread-52 count = 5198
	 * ThreadName = Thread-53 count = 5298
	 * ThreadName = Thread-54 count = 5398
	 * ThreadName = Thread-55 count = 5498
	 * ThreadName = Thread-56 count = 5598
	 * ThreadName = Thread-57 count = 5698
	 * ThreadName = Thread-58 count = 5798
	 * ThreadName = Thread-60 count = 5898
	 * ThreadName = Thread-61 count = 5998
	 * ThreadName = Thread-59 count = 6098
	 * ThreadName = Thread-62 count = 6198
	 * ThreadName = Thread-63 count = 6298
	 * ThreadName = Thread-64 count = 6398
	 * ThreadName = Thread-65 count = 6498
	 * ThreadName = Thread-66 count = 6598
	 * ThreadName = Thread-67 count = 6698
	 * ThreadName = Thread-68 count = 6798
	 * ThreadName = Thread-70 count = 6998
	 * ThreadName = Thread-69 count = 6898
	 * ThreadName = Thread-71 count = 7098
	 * ThreadName = Thread-72 count = 7198
	 * ThreadName = Thread-73 count = 7298
	 * ThreadName = Thread-74 count = 7398
	 * ThreadName = Thread-75 count = 7498
	 * ThreadName = Thread-76 count = 7598
	 * ThreadName = Thread-77 count = 7698
	 * ThreadName = Thread-78 count = 7798
	 * ThreadName = Thread-79 count = 7898
	 * ThreadName = Thread-80 count = 7998
	 * ThreadName = Thread-81 count = 8098
	 * ThreadName = Thread-82 count = 8198
	 * ThreadName = Thread-84 count = 8398
	 * ThreadName = Thread-83 count = 8298
	 * ThreadName = Thread-85 count = 8569
	 * ThreadName = Thread-86 count = 8669
	 * ThreadName = Thread-88 count = 8769
	 * ThreadName = Thread-87 count = 8669
	 * ThreadName = Thread-89 count = 8869
	 * ThreadName = Thread-90 count = 8969
	 * ThreadName = Thread-91 count = 9069
	 * ThreadName = Thread-92 count = 9169
	 * ThreadName = Thread-93 count = 9269
	 * ThreadName = Thread-94 count = 9369
	 * ThreadName = Thread-95 count = 9469
	 * ThreadName = Thread-96 count = 9569
	 * ThreadName = Thread-97 count = 9669
	 * ThreadName = Thread-98 count = 9769
	 * ThreadName = Thread-99 count = 9869
	 */

	public static void main(String[] args) {

		Chapter02_D5[] chapter02_d5 = new Chapter02_D5[100];

		for(int i = 0; i < 100; i ++ ) {
			chapter02_d5[i] = new Chapter02_D5();
		}

		for(int i = 0; i < 100; i ++ ) {
			chapter02_d5[i].start();
		}
	}


}
