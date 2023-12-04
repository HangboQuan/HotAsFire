package AQS;

import java.util.concurrent.CyclicBarrier;

/**
 * @author quanhangbo
 * @date 2023/12/4 23:35
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        cyclicBarrierMethod();
    }

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    public static void cyclicBarrierMethod() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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
