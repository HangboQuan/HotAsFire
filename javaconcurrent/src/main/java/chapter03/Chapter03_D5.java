package chapter03;

import java.lang.ref.WeakReference;

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
