package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-27 下午7:14
 */
public class Chapter03_C7 {
	// 变量值共享可以使用 public static的形式， 如果每一个线程都有自己的共享变量 -> ThreadLocal
	// ThreadLocal主要解决的就是每个线程绑定自己的值，可以将ThreadLocal存放数据的盒子，盒子里可以存储每个线程的私有数据
	public static ThreadLocal t1 = new ThreadLocal();

	// ThreadLocal解决的是变量在不同线程间的隔离性
	public static void main(String[] args) {
		if (t1.get() == null) {
			System.out.println("从来没有存放过值");
			t1.set("我的值");
		}
		System.out.println(t1.get());
		System.out.println(t1.get());
	}
}
