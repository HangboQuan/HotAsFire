package AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author quanhangbo
 * @date 2023/12/6 15:11
 */
public class SemaphoreTest {

    /**
     * Semaphore(信号量)：用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用公共资源
     * 举例：控制流量的红绿灯，某条路需要限制车流量，只允许一百辆车在这条路上行驶，其他都必须等待，如果路上有5辆车已经离开了该路
     * 那么后面就允许5辆车驶入马路
     *
     *
     * 实际应用场景：Semaphore可以用于流量控制，特别是公用资源的应用场景，比如：数据库连接
     * 假如有一个需求，要读几万个文件的数据，因为都是IO密集型任务，可以启动几十个线程并发读取，然后存储到数据库中，而数据库的连接数只有10个
     * 这时我们必须控制只有10个线程同时获取数据库连接保存数据，就可以用Semaphore来做流量控制
     * @param args
     */
    private static final int THREAD_COUNT= 30;
    private static ExecutorService threadpool = Executors.newFixedThreadPool(THREAD_COUNT);
    // permits接受一个整型的数字 表示可用的许可证数量 也即最大并发数为10
    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i ++ ) {
            int finalI = i;
            threadpool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 获取一个许可证
                        s.acquire();
                        System.out.println("save " + finalI + " data finished!");
                        // 归还一个许可证
                        s.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        threadpool.shutdown();

    }
}
