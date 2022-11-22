package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/22 21:56
 */
public class Chapter03_C0 {
	volatile private boolean threadsold = false;
	
	synchronized public void methodA() {
		try {
			while(threadsold) {
				this.wait();
			}
			for(int i = 0; i < 5; i ++ ) {
				System.out.println("print *******************");
			}
			threadsold = true;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void methodB() {
		try {
			while(!threadsold) {
				this.wait();
			}
			for(int i = 0; i < 5; i ++ ) {
				System.out.println("print $$$$$$$$$$$$$$$$$$$$$");
			}
			threadsold = false;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter03_C0_ThreadA extends Thread {
	
	private Chapter03_C0 chapter03_c0;
	
	public Chapter03_C0_ThreadA(Chapter03_C0 chapter03_c0) {
		this.chapter03_c0 = chapter03_c0;
	}
	
	@Override
	public void run() {
		chapter03_c0.methodA();
	}
}

class Chapter03_C0_ThreadB extends Thread {

	private Chapter03_C0 chapter03_c0;
	
	public Chapter03_C0_ThreadB(Chapter03_C0 chapter03_c0) {
		this.chapter03_c0 = chapter03_c0;
	}
	
	@Override
	public void run() {
		chapter03_c0.methodB();
	}
}

class Chapter03_C0_01 {
	
	public static void main(String[] args) {
		/**
		 * 这样就实现了交替打印
		 */
		Chapter03_C0 chapter03_c0 = new Chapter03_C0();
		for(int i = 0; i < 10; i ++ ) {
			Chapter03_C0_ThreadA chapter03_c0_threadA = new Chapter03_C0_ThreadA(chapter03_c0);
			chapter03_c0_threadA.start();
			
			Chapter03_C0_ThreadB chapter03_c0_threadB = new Chapter03_C0_ThreadB(chapter03_c0);
			chapter03_c0_threadB.start();
		}
	}
}