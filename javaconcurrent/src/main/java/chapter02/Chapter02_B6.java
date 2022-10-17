package chapter02;

/**
 * @author quanhangbo
 * @date 2022/10/18 7:17
 */
public class Chapter02_B6 {

	private String username;
	private String password;
	
	
	public void setUsernameAndPassword(String uparm, String pparm) {
		String anyString = new String();
		
		/**
		 * synchronized(非this)同步代码块，对象监视器必须是同一个对象。如果不是同一个对象监视器，
		 * 则异步执行
		 */
		synchronized (anyString){
			try {
				System.out.println("threadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				username = uparm;
				Thread.sleep(2000);
				password = pparm;
				
				System.out.println("threadName = " + Thread.currentThread().getName() + " username = " + username + " password = " + password +
						                   System.currentTimeMillis());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class Chapter02_B6_01 extends Thread {

	private Chapter02_B6 chapter02_b6;
	
	public Chapter02_B6_01(Chapter02_B6 chapter02_b6) {
		this.chapter02_b6 = chapter02_b6;
	}
	
	@Override
	public void run() {
		chapter02_b6.setUsernameAndPassword("A", "AA");
		
	}
}

class Chapter02_B6_02 extends Thread {

	private Chapter02_B6 chapter02_b6;
	
	public Chapter02_B6_02(Chapter02_B6 chapter02_b6) {
		this.chapter02_b6 = chapter02_b6;
	}
	
	@Override
	public void run() {
		chapter02_b6.setUsernameAndPassword("B", "BB");
		
	}
}

class Chapter02_B6_03 {

	/**
	 * result:
	 * threadName = ThreadA 1666010728821
	 * threadName = ThreadA username = A password = AA1666010730822
	 * threadName = ThreadB 1666010730822
	 * threadName = ThreadB username = B password = BB1666010732822
	 * @param args
	 */
	public static void main(String[] args){
		Chapter02_B6 chapter02_b6 = new Chapter02_B6();
		
		Chapter02_B6_01 chapter02_b6_01 = new Chapter02_B6_01(chapter02_b6);
		chapter02_b6_01.setName("ThreadA");
		chapter02_b6_01.start();
		
		Chapter02_B6_02 chapter02_b6_02 = new Chapter02_B6_02(chapter02_b6);
		chapter02_b6_02.setName("ThreadB");
		chapter02_b6_02.start();
	}
	
}

