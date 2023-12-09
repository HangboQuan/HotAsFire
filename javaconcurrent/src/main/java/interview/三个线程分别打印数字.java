package interview;

/**
 * @author quanhangbo
 * @date 2023/12/9 17:02
 */
public class 三个线程分别打印数字 {

    /**
     * 三个线程分别打印如下结果, num=75时结束打印:
     * Thread1: 1,4,7...
     * Thread2: 2,5,8...
     * Thread3: 3,6,9...
     */

    public static void main(String[] args) {
        Thread threadA = new Thread(new PrintNum("ThreadA", 0));
        Thread threadB = new Thread(new PrintNum("ThreadB", 1));
        Thread threadC = new Thread(new PrintNum("ThreadC", 2));


        /**
         * ThreadA:1
         * ThreadB:2
         * ThreadC:3
         * ThreadA:4
         * ThreadB:5
         * ThreadC:6
         * ThreadA:7
         * ThreadB:8
         * ThreadC:9
         * ThreadA:10
         * ThreadB:11
         * ThreadC:12
         * ThreadA:13
         * ThreadB:14
         * ThreadC:15
         * ThreadA:16
         * ThreadB:17
         * ThreadC:18
         * ThreadA:19
         * ThreadB:20
         * ThreadC:21
         * ThreadA:22
         * ThreadB:23
         * ThreadC:24
         * ThreadA:25
         * ThreadB:26
         * ThreadC:27
         * ThreadA:28
         * ThreadB:29
         * ThreadC:30
         * ThreadA:31
         * ThreadB:32
         * ThreadC:33
         * ThreadA:34
         * ThreadB:35
         * ThreadC:36
         * ThreadA:37
         * ThreadB:38
         * ThreadC:39
         * ThreadA:40
         * ThreadB:41
         * ThreadC:42
         * ThreadA:43
         * ThreadB:44
         * ThreadC:45
         * ThreadA:46
         * ThreadB:47
         * ThreadC:48
         * ThreadA:49
         * ThreadB:50
         * ThreadC:51
         * ThreadA:52
         * ThreadB:53
         * ThreadC:54
         * ThreadA:55
         * ThreadB:56
         * ThreadC:57
         * ThreadA:58
         * ThreadB:59
         * ThreadC:60
         * ThreadA:61
         * ThreadB:62
         * ThreadC:63
         * ThreadA:64
         * ThreadB:65
         * ThreadC:66
         * ThreadA:67
         * ThreadB:68
         * ThreadC:69
         * ThreadA:70
         * ThreadB:71
         * ThreadC:72
         * ThreadA:73
         * ThreadB:74
         * ThreadC:75
         */
        threadA.start();
        threadB.start();
        threadC.start();
    }


    static class PrintNum implements Runnable {

        private String threadName;
        private int flag;
        private static int num;
        private final Object lock = new Object();
        private static final int MAX_VALUE = 75;

        public PrintNum(String threadName, int flag) {
            this.threadName = threadName;
            this.flag = flag;
        }

        @Override
        public void run() {
            while (num < MAX_VALUE) {
                synchronized (lock) {
                    if (num % 3 == flag) {
                        try {
                            System.out.println(threadName + ":" +  (++ num));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        Thread.sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


