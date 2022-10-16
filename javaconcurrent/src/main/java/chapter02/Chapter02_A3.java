package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/11 23:17
 */
public class Chapter02_A3 {
	
	synchronized public void methodA() {
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void methodB() {
		
		try{
			long start = System.currentTimeMillis();
			System.out.println("currentThreadName :" + Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("END " + (System.currentTimeMillis() - start));
			
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}
}

class Chapter02_A3_01 extends Thread{
	
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_01(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodA();
	}
	
}

class Chapter02_A3_02 extends Thread {
	private Chapter02_A3 chapter02_a3;
	
	public Chapter02_A3_02(Chapter02_A3 chapter02_a3){
		this.chapter02_a3 = chapter02_a3;
	}
	
	@Override
	public void run() {
		chapter02_a3.methodB();
	}
}

class Chapter02_A3_03 {

	/**
	 * ThreadA调用methodA, 虽然ThreadA持有了object对象锁，但是ThreadB还是可以异步的调用非synchronized类型方法？ 不是说synchronized锁住的
	 * object对象，但是为什么同一个实例对象调用非同步方法的时候，还是执行的异步，那么这样的话会不会带来脏数据呢？答案是肯定的，会带来脏读的问题
	 *
	 * ThreadA调用加synchronized的methodA, 其实synchronized就持有了实例对象来调用方法的对象锁，如果别的线程也想调用方法methodA,只能等到ThreadA
	 * 释放对象锁之后，才可以; ThreadA调用加synchronized的methodA，ThreadB调用非synchronized的methodB, 即使使用的同一个实例对象，也互不影响
	 * 异步执行
	 *
	 * 如果methodA和methodB都给这个方法加了synchronized，那么同一个实例对象访问的时候，就是同步执行的
	 * result:
	 * currentThreadName :ThreadA
	 * currentThreadName :ThreadB
	 * end 5000
	 * END 5000
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_A3 chapter02_a3 = new Chapter02_A3();
		Chapter02_A3_01 chapter02_a3_01 = new Chapter02_A3_01(chapter02_a3);
		Chapter02_A3_02 chapter02_a3_02 = new Chapter02_A3_02(chapter02_a3);
		
		chapter02_a3_01.setName("ThreadA");
		chapter02_a3_02.setName("ThreadB");
		
		chapter02_a3_01.start();
		chapter02_a3_02.start();
	}
}
