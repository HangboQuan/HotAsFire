package chapter01;

/**
 * @author quanhangbo
 * @date 2022/9/16 10:01
 */

/**
 * 解决线程安全问题
 */
public class Chapter01_A5 extends Thread{
	
	private int count = 10;

	/**
	 * 在run()前面加入synchronized,使多个线程执行run(),以排队的方式进行处理
	 * 当一个线程调用run(),先判断run()方法有没有被上锁,如果上锁,说明其他线程正在调用run()
	 * 必须等其他线程对run()调用结束后才可以执行run()
	 *
	 *
	 * 实现了排队调用run()的目的,按顺序对count=count-1
	 * synchronized可以在任意对象及方法上加锁,加锁的代码被称为"互斥区|临界区"
	 *
	 * 当一个线程要执行同步方法的代码时, 首先尝试去拿这把锁，如果能拿到这把锁，
	 * 就可以执行synchronized的代码。如果不能拿到这把锁，那么这个线程就会不断地尝试拿这把锁
	 * 这样就会多个线程同时去争抢这把锁
	 *
	 * 非线程安全：多个线程对同一个对象的同一个实例变量进行操作时出现值被更改、值不同步的情况
	 * 进而影响程序的执行流程
	 */
	@Override
	synchronized public void run(){
		while(count > 0) {
			count --;
			System.out.println("由 " + Thread.currentThread().getName() + "消费了1个库存，剩余库存为" + count);
		}
	}

	/**
	 * result:
	 * 由 ThreadA消费了1个库存，剩余库存为9
	 * 由 ThreadA消费了1个库存，剩余库存为8
	 * 由 ThreadA消费了1个库存，剩余库存为7
	 * 由 ThreadA消费了1个库存，剩余库存为6
	 * 由 ThreadA消费了1个库存，剩余库存为5
	 * 由 ThreadA消费了1个库存，剩余库存为4
	 * 由 ThreadA消费了1个库存，剩余库存为3
	 * 由 ThreadA消费了1个库存，剩余库存为2
	 * 由 ThreadA消费了1个库存，剩余库存为1
	 * 由 ThreadA消费了1个库存，剩余库存为0
	 * @param args
	 */
	public static void main(String[] args){
		Chapter01_A5 chapter01_a5 = new Chapter01_A5();
		Thread threadA = new Thread(chapter01_a5, "ThreadA");
		Thread threadB = new Thread(chapter01_a5, "ThreadB");
		Thread threadC = new Thread(chapter01_a5, "ThreadC");
		
		threadA.start();
		threadB.start();
		threadC.start();
	}
	
}

class LoginServlet{
	private static String usernameRef;
	private static String passwordRef;

	/**
	 * 非线程安全：
	 * result:
	 * username=b password=bb
	 * username=b password=aa
	 *
	 * 给方法前加了synchronized关键字后, 线程安全：
	 * username=a password=aa
	 * username=b password=bb
	 * @param username
	 * @param password
	 */
	synchronized public static void doPost(String username, String password){
		try{
			usernameRef = username;
			if(username.equals("a")){
				Thread.sleep(5000);
			}
			passwordRef = password;
			System.out.println("username=" + usernameRef + " password=" + passwordRef);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}

class ALogin extends Thread{
	
	@Override
	public void run(){
		LoginServlet.doPost("a", "aa");
	}
}

class BLogin extends Thread{
	
	@Override
	public void run(){
		LoginServlet.doPost("b", "bb");
	}
}

class Chapter01_A5_01{
	
	public static void main(String[] args){
		ALogin a = new ALogin();
		a.start();
		BLogin b = new BLogin();
		b.start();
	}
}
