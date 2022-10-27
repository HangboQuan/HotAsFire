package chapter02;


/**
 * @author quanhangbo
 * @date 22-10-27 下午3:58
 */
public class Chapter02_D3 implements Runnable {

	//关键字volatile主要作用是使变量在多个线程中可见
	private boolean isContinuePrint = true;

	public boolean isContinuePrint() {
		return isContinuePrint;
	}

	public void setContinuePrint(boolean isContinuePrint) {
		this.isContinuePrint = isContinuePrint;
	}

	public void serviceMethod() {
		int i = 0;
		while(isContinuePrint == true) {
			i ++;
			System.out.println("ThreadName " + Thread.currentThread().getName() + " " + i);
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {
		serviceMethod();
	}
}

class Chapter02_D3_01 {


	/**
	 * stop it, stop thread = main
	 * ThreadName Thread-0 1
	 *
	 * 这个代码运行在服务器上会出现死循环 解决办法是使用volatile关键字
	 *
	 * volatile关键字的作用: 强制从公共堆栈中取得变量的值，而不是从线程私有的数据栈取得变量值
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * main线程处理while循环 导致不能执行后面的代码
		 */
		Chapter02_D3 chapter02_d3 = new Chapter02_D3();

		new Thread(chapter02_d3).start();
		System.out.println("stop it, stop thread = " + Thread.currentThread().getName());
		chapter02_d3.setContinuePrint(false);

	}

}
