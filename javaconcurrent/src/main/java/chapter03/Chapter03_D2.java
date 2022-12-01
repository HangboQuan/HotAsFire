package chapter03;

/**
 * @author quanhangbo
 * @date 2022/12/1 9:35
 */
public class Chapter03_D2 {

/**
 * Java的四种引用：强引用、软引用、弱引用、虚引用
 * 1. java 内存的分配和回收 是由JVM去负责的，一个对象是否被回收，主要看是否有引用指向此对象
 * (可达性分析)
 * 2. 四种引用的目的是：可以让程序员通过代码的方式来决定某个对象的生命周期; 有利于垃圾回收
 * ThreadLocal的问题
 * 1. 线程安全问题
 *
 * 2. 内存泄露
 *
 * 3. inheritableThreadLocal的线程安全问题
 *
 **/

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Chapter03_D2 被回收了");
	}
	
	public static void main(String[] args) {
		Chapter03_D2 chapter03_d2 = new Chapter03_D2();
		chapter03_d2 = null;
		System.gc();
	}
	
}


