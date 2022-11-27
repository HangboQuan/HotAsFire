package chapter03;

/**
 * @author quanhangbo
 * @date 22-11-27 下午4:33
 */
public class Chapter03_C6 extends Thread {

	@Override
	public synchronized void run() {

		try {
			System.out.println(" begin chapter03_c6 ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.println(" end chapter03_c6 ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class Chapter03_C6_01 extends Thread {

	private Chapter03_C6 chapter03_c6;

	public Chapter03_C6_01(Chapter03_C6 chapter03_c6) {
		this.chapter03_c6 = chapter03_c6;
	}

	@Override
	public void run() {

		try {
			synchronized (chapter03_c6) {
				System.out.println(" begin chapter03_c6_01 ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
				Thread.sleep(5000);
				System.out.println(" end chapter03_c6_01 ThreadName = " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}


class Chapter03_C6_02 {

	/**
	 * b.join(2000)抢到chapter03_c6锁，wait(2000)释放锁 (每次必然是先要执行b.join吗，大概率是的)-> chapter03_c6_01抢到锁，打印begin并且sleep(5000)，打印end释放锁 ->
	 *
	 * join(2000) 和 chapter03_c6争抢锁（这里有个问题? 前面第一次就说b.join抢到锁就释放了，在这里为什么还会和其他线程抢锁?）
	 * -> join(2000再次抢到锁之后，时间已过)释放锁 打印main end ->
	 *
	 * chapter03_c6抢到锁 之后打印begin -> 休息5s -> 打印end
	 *
	 *
	 *
	 * result0:
	 *  begin chapter03_c6_01 ThreadName = Thread-1 1669544086004
	 *  end chapter03_c6_01 ThreadName = Thread-1 1669544091005
	 *  main end 1669544091005
	 *  begin chapter03_c6 ThreadName = Thread-0 1669544091005
	 *  end chapter03_c6 ThreadName = Thread-0 1669544096006
	 *
	 * b.join(2000)抢到chapter03_c6锁，wait(2000)释放锁 -> chapter03_c6_01抢到锁，打印begin并且sleep(5000)，打印end
	 *
	 * join(2000) 和 chapter03_c6抢锁(同问? join为什么能继续抢锁) chapter03_c6先抢到锁 打印begin -> sleep(5000) -> end
	 *
	 * 打印main end
	 *
	 * result1:
	 *  begin chapter03_c6_01 ThreadName = Thread-1 1669544320999
	 *  end chapter03_c6_01 ThreadName = Thread-1 1669544325999
	 *  begin chapter03_c6 ThreadName = Thread-0 1669544325999
	 *  end chapter03_c6 ThreadName = Thread-0 1669544331000
	 *  main end 1669544331000
	 *
	 * b.join(2000)抢到chapter03_c6锁，wait(2000)释放锁 -> chapter03_c6_01抢到锁，打印begin并且sleep(5000)，打印end
	 * join(2000) 和 chapter03_c6抢锁，join(2000)抢到锁发现时间已过，释放锁 -> chapter03_c6线程begin - main end也异步输出
	 *
	 * -> chapter03_c6 end
	 *  result2:
	 *  begin chapter03_c6_01 ThreadName = Thread-1 1669544886037
	 *  end chapter03_c6_01 ThreadName = Thread-1 1669544891037
	 *  begin chapter03_c6 ThreadName = Thread-0 1669544891038
	 *  main end 1669544891038
	 *  end chapter03_c6 ThreadName = Thread-0 1669544896038
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Chapter03_C6 chapter03_c6 = new Chapter03_C6();

			Chapter03_C6_01 chapter03_c6_01 = new Chapter03_C6_01(chapter03_c6);
			chapter03_c6_01.start();
			chapter03_c6.start();
			// 线程.start()之后 一定是存活的吗? 答案是是的，只要start()了，线程就认为是存活的了，不管是否有CPU调度
			chapter03_c6.join(2000);
			// 假如不加上面join的代码，大概率是先执行main end，然后再执行另外两个线程
			System.out.println(" main end " + System.currentTimeMillis());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}