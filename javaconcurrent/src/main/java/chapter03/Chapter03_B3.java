package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-10 下午9:00
 */
public class Chapter03_B3 {
	public static String value = "";
}


class Chapter03_B3_01 {
	private String lock;

	public Chapter03_B3_01(String lock) {
		this.lock = lock;
	}

	public void setValue() {

		try {
			synchronized (lock) {
				while(!Chapter03_B3.value.equalsIgnoreCase("")) {
					System.out.println("生产者" +  Thread.currentThread().getName() + "waiting了*");
					lock.wait();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
