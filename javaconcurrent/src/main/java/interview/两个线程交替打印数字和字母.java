package interview;

/**
 * @author quanhangbo
 * @date 2023/12/9 18:37
 */
public class 两个线程交替打印数字和字母 {


    private static boolean flag = false;
    private static final Object obj = new Object();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadPrintNum());
        Thread thread2 = new Thread(new ThreadPrintWord());

        thread1.start();
        thread2.start();

    }

    /**
     * 1A2B3C4D5E6F7G8H9I10J11K12L13M14N15O16P17Q18R19S20T21U22V23W24X25Y26Z27A28B29C30D31E32F33G34H35I36J37K38L39M40N41O42P43Q44R45S46T47U48V49W50X51Y52Z
     */
    static class ThreadPrintWord implements Runnable {
        @Override
        public void run() {
            synchronized (obj) {
                for (int c = 65; c <= 65 + 26;) {
                    while (!flag) {
                        try {
                            obj.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print((char)c);
                    flag = false;
                    obj.notify();
                    c ++;
                    if (c >= 65 + 26) {
                        c = 65;
                    }
                }
            }
        }
    }

    static class ThreadPrintNum implements Runnable {


        /**
         *
         * 两个线程直接的wait和notify条件，通过while条件来判断什么条件需要等待，否则再写notify
         */
        @Override
        public void run() {
            synchronized (obj) {
                for (int i = 1; i <= 52; i ++ ) {
                    while (flag) {
                        try {
                            obj.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(i);
                    flag = true;
                    obj.notify();
                }
            }
        }
    }
}
