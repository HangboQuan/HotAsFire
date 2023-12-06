package AQS;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author quanhangbo
 * @date 2023/12/6 22:41
 */
public class ExchangerTest {

    /**
     * Exchanger(交换者) 是一个用于线程间协作的工具类 用于数据交换
     * 它提供一个同步点，在这个同步点，两个线程可以交换彼此数据 这两个线程通过exchange交换数据
     * 如果第一个线程先执行exchange() 它会等待第二个线程也执行exchange() 当两个线程都到达同步点时，这两个线程就可以交换数据
     * @param args
     *
     *
     * 应用场景：校对工作
     */

    private static final Exchanger<String> exgr = new Exchanger<>();
    private static ExecutorService threadpool = Executors.newFixedThreadPool(2);
    public static void main(String[] args) {

        // 如果两个线程 有一个没有执行exchange()方法 则会一直等待 如果担心有特殊情况发生，避免一直等待 可以使用exchange(V x, long timeout, TimeUnit unit)
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水A";
                    exgr.exchange(A);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        threadpool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水B";
                    String A = exgr.exchange(B);
                    System.out.println("A和B数据是否一致：" + A.equals(B) + ", A录入的是：" + A + ", B录入的是：" + B);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        threadpool.shutdown();

    }
}
