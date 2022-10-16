package chapter02;

/**
 * @author quanhangbo
 * @date 22-10-16 下午4:21
 */
public class Chapter02_A6 {

	public int i = 10;
	synchronized public void operateIParentMethod() {
		try {
			i --;
			System.out.println("parent print i = " + i);
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Chapter02_A6_01 extends Chapter02_A6 {

	synchronized public void operateISubMethod() {
		try {
			while (i > 0) {
				i --;
				System.out.println("sub parent i = " + i);
				this.operateIParentMethod();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Chapter02_A6_02 extends Thread {

	@Override
	public void run() {
		Chapter02_A6_01 chapter02_a6_01 = new Chapter02_A6_01();

		chapter02_a6_01.operateISubMethod();
	}


	public static void main(String[] args) {

		/**
		 * 可重入锁也支持父子类继承的环境，子类是可以通过'可重入锁'调用父类的同步方法
		 * result:
		 * sub parent i = 9
		 * parent print i = 8
		 * sub parent i = 7
		 * parent print i = 6
		 * sub parent i = 5
		 * parent print i = 4
		 * sub parent i = 3
		 * parent print i = 2
		 * sub parent i = 1
		 * parent print i = 0
		 */

		Chapter02_A6_02 chapter02_a6_02 = new Chapter02_A6_02();
		chapter02_a6_02.start();
	}
}