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




