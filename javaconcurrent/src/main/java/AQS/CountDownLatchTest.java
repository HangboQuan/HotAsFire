package AQS;

import java.util.concurrent.CountDownLatch;

/**
 * @author quanhangbo
 * @date 2023/12/4 23:14
 */
public class CountDownLatchTest {
    /**
     * CountDownLatch的作用：允许一个或者多个线程等待其他线程完成操作
     * 需求：需要解析一个Excel里多个sheet的数据，可以使用多线程每个线程解析一个sheet里的数据，等到所有的sheet解析完成后，提示完成
     */

    public static void main(String[] args) throws Exception {
//        joinMethod();
        countDownLatchMethod();
    }

    public static void joinMethod() throws Exception {
        // 使用join方法，让主线程等待其他线程完成任务后再继续执行
        // 实现原理：不停的检查join线程是否存活，如果join线程存活则让当前线程永远等待

        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finished!");
            }
        });

        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finished!");
            }
        });

        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("all sheet parser finished!");

    }

    public static void countDownLatchMethod() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread parser1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finished!");
                countDownLatch.countDown();
            }
        });

        Thread parser2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finished!");
                countDownLatch.countDown();
            }
        });

        parser1.start();
        parser2.start();

        // 阻塞当前线程 直到N变为0
        countDownLatch.await();
        System.out.println("all sheet parser finished!");
    }
}
