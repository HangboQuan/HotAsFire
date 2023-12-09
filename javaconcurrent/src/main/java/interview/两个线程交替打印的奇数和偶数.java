package interview;

/**
 * @author quanhangbo
 * @date 2023/12/9 16:32
 */
public class 两个线程交替打印的奇数和偶数 {
    /**
     * 两个线程交替打印100之内的奇数和偶数
     */

    private static final Object object = new Object();
    private static int num = 1;
    private static final int MAX_VALUE = 100;
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num <= MAX_VALUE) {
                    synchronized (object) {
                        // 奇数
                        if ((num & 1) != 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + num);
                            try {
                                object.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            num ++;
                            object.notify();
                        }
                    }
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (num <= MAX_VALUE) {
                    synchronized (object) {
                        // 奇数
                        if ((num & 1) == 0) {
                            System.out.println(Thread.currentThread().getName() + ":" + num);
                            try {
                                object.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            num ++;
                            object.notify();
                        }
                    }
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
    /*
    这种写法有很大问题

    public static void printOddEven() {
        while (num <= MAX_VALUE) {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + ":" + num);
                object.notify();
                // 偶数
                if ((num & 1) == 0) {
                    try {
                        object.wait();
                    } catch (Exception e) {

                    }
                }
                num ++;
            }
        }
    }*/


}
