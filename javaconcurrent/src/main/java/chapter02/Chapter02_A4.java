package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-15 下午7:57
 */
public class Chapter02_A4 {

	public String username = "A";
	public String password = "AA";

	synchronized public void setValue(String username, String password){

		try {
			this.username = username;
			Thread.sleep(5000);
			this.password = password;

			System.out.println("setValue method value name = " + Thread.currentThread().getName() +
					" username = " + username + " password = " + password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	synchronized public void getValue() {
		System.out.println("getValue method value name = " + Thread.currentThread().getName() +
				" username = " + username + " password = " + password);
	}
}

class Chapter02_A4_01 extends Thread {
	public Chapter02_A4 chapter02_a4;

	public Chapter02_A4_01(Chapter02_A4 chapter02_a4){
		super();
		this.chapter02_a4 = chapter02_a4;
	}

	@Override
	public void run() {
		chapter02_a4.setValue("B", "BB");
	}

}

class Chapter02_A4_02 {

	public static void main(String[] args) {

		/**
		 * 从结果来看就是非同步的，要想实现同步，就需要给getValue()加synchronized
		 * result:
		 * getValue method value name = main username = B password = AA
		 * setValue method value name = ThreadA username = B password = BB
		 */
		try {
			Chapter02_A4 chapter02_a4 = new Chapter02_A4();

			Chapter02_A4_01 chapter02_a4_01 = new Chapter02_A4_01(chapter02_a4);
			chapter02_a4_01.setName("ThreadA");
			chapter02_a4_01.start();
//			Thread.sleep(1000);
			chapter02_a4.getValue();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}