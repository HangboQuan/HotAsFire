1.非线程安全是怎么出现的？哪些情况会导致线程安全？  
>非线程安全：多个线程对同一个对象的实例变量并发进行访问，由于某些语句非原子性(i ++)，就会出现脏读，即取到的数据是被别的线程更改过的  
线程安全：多个线程对同一个对象的实例变量并发进行访问，获取变量的值是经过同步处理的，不会出现脏读的情况  

多个线程并发访问同一个公有对象，可能导致线程安全问题
```java
public class Chapter02_A2 {
	synchronized public void methodA() {
		try{
			System.out.println("begin currentThread name:" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}

class Chapter02_A2_01 extends Thread{
	private Chapter02_A2 chapter02_a2;
	public Chapter02_A2_01(Chapter02_A2 chapter02_a2) {
		this.chapter02_a2 = chapter02_a2;
	}
	
	@Override
	public void run() {
		chapter02_a2.methodA();
	}
}

class Chapter02_A2_02 extends Thread{
	private Chapter02_A2 chapter02_a2;
	public Chapter02_A2_02(Chapter02_A2 chapter02_a2) {
		this.chapter02_a2 = chapter02_a2;
	}
	
	@Override
	public void run() {
		chapter02_a2.methodA();
	}
}

class Chapter02_A2_03 {
	
	public static void main(String[] args){
		
		/**
		 * 只有共享资源的读写才需要同步化，非共享资源没必要同步化(如方法内部的私有变量)
		 * 未加synchronized同步
		 * begin currentThread name:ThreadA
		 * begin currentThread name:ThreadB
		 * end
		 * end
		 *
		 * 加上synchronized同步锁
		 * begin currentThread name:ThreadA
		 * end
		 * begin currentThread name:ThreadB
		 * end
		 */
		// 如果这里创建的是2个Chapter02_A2对象，并且分别把它们作为参数传递，调用run()则仍然是异步执行
		Chapter02_A2 chapter02_a2 = new Chapter02_A2();
		
	        
		Chapter02_A2_01 chapter02_a2_01 = new Chapter02_A2_01(chapter02_a2);
		chapter02_a2_01.setName("ThreadA");
		chapter02_a2_01.start();
		
		Chapter02_A2_02 chapter02_a2_02 = new Chapter02_A2_02(chapter02_a2);
		chapter02_a2_02.setName("ThreadB");
		chapter02_a2_02.start();
		
	}
}
```

2.synchronized锁方法  
3.synchronized锁class  
4.synchronized锁this/其他对象  
5.volatile的作用  
6.volatile和synchronized的区别和使用情况  
