package chapter03;

/**
 * @author quanhangbo
 * @date 2022/11/23 23:26
 */
public class Chapter03_C1 {
	volatile private String threadName = "ThreadA";
	volatile private int value = 1;
	
	synchronized public void threadAPrint() {
		try {
			int count = 0;
			if (!"ThreadA".equalsIgnoreCase(threadName)) {
				wait();
			}
			while (count < 25 && value <= 100) {
				System.out.println(Thread.currentThread().getName() + " " + value);
				value ++;
				count ++;
			}
			threadName = "ThreadB";
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	synchronized public void threadBPrint() {
		try {
			int count = 0;
			if (!"ThreadB".equalsIgnoreCase(threadName)) {
				wait();
			}
			while (count < 25 && value <= 100) {
				System.out.println(Thread.currentThread().getName() + " " + value);
				value ++;
				count ++;
			}
			threadName = "ThreadC";
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	synchronized public void threadCPrint() {
		try {
			int count = 0;
			if (!"ThreadC".equalsIgnoreCase(threadName)) {
				wait();
			}
			while (count < 25 && value <= 100) {
				System.out.println(Thread.currentThread().getName() + " " + value);
				value ++;
				count ++;
			}
			threadName = "ThreadA";
			notify();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ThreadAPrint extends Thread {
	private Chapter03_C1 chapter03_c1;
	
	public ThreadAPrint(Chapter03_C1 chapter03_c1) {
		this.chapter03_c1 = chapter03_c1;
	}
	
	@Override
	public void run() {
		while (true) {
			chapter03_c1.threadAPrint();
		}
	}
}

class ThreadBPrint extends Thread {
	private Chapter03_C1 chapter03_c1;
	
	public ThreadBPrint(Chapter03_C1 chapter03_c1) {
		this.chapter03_c1 = chapter03_c1;
	}
	
	@Override
	public void run() {
		while (true) {
			chapter03_c1.threadBPrint();
		}
	}
}

class ThreadCPrint extends Thread {
	private Chapter03_C1 chapter03_c1;
	
	public ThreadCPrint(Chapter03_C1 chapter03_c1) {
		this.chapter03_c1 = chapter03_c1;
	}
	
	@Override
	public void run() {
		while (true) {
			chapter03_c1.threadCPrint();
		}
		
	}
}


class Chapter03_C1_01 {
	
	public static void main(String[] args) {
		Chapter03_C1 chapter03_c1 = new Chapter03_C1();
		ThreadAPrint threadAPrint = new ThreadAPrint(chapter03_c1);
		ThreadBPrint threadBPrint = new ThreadBPrint(chapter03_c1);
		ThreadCPrint threadCPrint = new ThreadCPrint(chapter03_c1);
		
		threadAPrint.setName("ThreadA");
		threadBPrint.setName("ThreadB");
		threadCPrint.setName("ThreadC");
		
		threadAPrint.start();
		threadBPrint.start();
		threadCPrint.start();
	}
}