package AQS;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author quanhangbo
 * @date 2023/12/4 23:35
 */
public class CyclicBarrierTest {

    /**
     * CyclicBarrier字面意思是可循环使用的屏障(Barrier), 作用是：让一组线程到达一个屏障(同步点)，
     * 直到最后一个线程到达屏障(同步点)时，屏障才会开门，所有被屏障拦截的线程才会继续运行
     * @param args
     */
    public static void main(String[] args) {
//        cyclicBarrierMethod();
//        cyclicBarrierAdvanceMethod();
        CyclicBarrierService cyclicBarrierService = new CyclicBarrierService();
    }

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

    // 用于线程到达屏障时，优先执行barrierAction，方便处理更复杂的业务场景
    static CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println("first commit");
        }
    });

    public static void cyclicBarrierAdvanceMethod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cyclicBarrier1.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(1);
            }
        }).start();

        try {
            cyclicBarrier1.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }


    public static void cyclicBarrierMethod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // await()表示线程已经到达屏障
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("alibaba");
            }
        }).start();

        try {
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("meituan");
    }
}

class CyclicBarrierService implements Runnable {

    /**
     * CyclicBarrier一个参数的构造函数是指定相互等待的线程个数
     * 两个参数的构造函数是指定了相互等待的线程个数和栅栏打开之后触发的操作
     *
     *
     * CountDownLatch和CyclicBarrier的区别：
     * 1. 强调的重点不一样
     *  CountDownLatch 更加强调一个或者一组线程等待另外一组线程。举个例子：我在家请4位同事吃饭，我一直在await()，每来一位同事我执行countDown()，直到所有人来齐了再开始吃饭
     *  CyclicBarrier 更加强调一组线程之间的相互等待。举个例子：周末同事们一起约去爬山，相约在景区入口处集合，等全部人集合之后再去爬山，此时景区入口就是同步点，所有同事到齐这个同步点就会打开
     *
     * 2. 可重用性不一样
     *  CountDownLatch计数归零之后不能重复利用了
     *  CyclicBarrier可以reset进行重复利用，因此能处理更为复杂的业务场景。例如：如果计算发生错误，可以重置计数器，并让线程重新执行一次
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);
    private Executor executor = Executors.newFixedThreadPool(4);

    private ConcurrentHashMap<String, Integer> sheetCount = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i ++ ) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheetCount.put(Thread.currentThread().getName(), 1);
                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetCount.entrySet()) {
            result += sheet.getValue();
        }

        System.out.println(result);
    }

    public static void main(String[] args) {
        CyclicBarrierService cyclicBarrierService = new CyclicBarrierService();
        cyclicBarrierService.count();
    }
}
