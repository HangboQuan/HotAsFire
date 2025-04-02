package com.alibaba.strategy;

/**
 * @author quanhangbo
 * @date 2025-03-31 18:17
 */
public class ThreadPrint {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadPrint.class) {
                    while (index < 100) {
                        if (index % 3 == 0) {
                            System.out.println(Thread.currentThread().getName() + " " + ans[index ++]);
                            ThreadPrint.class.notifyAll();
                        } else {
                            try {
                                ThreadPrint.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadPrint.class) {
                    while (index < 100) {
                        if (index % 3 == 1) {
                            System.out.println(Thread.currentThread().getName() + " " + ans[index ++]);
                            ThreadPrint.class.notifyAll();
                        } else {
                            try {
                                ThreadPrint.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (ThreadPrint.class) {
                    while (index < 100) {
                        if (index % 3 == 2) {
                            System.out.println(Thread.currentThread().getName() + " " + ans[index ++]);
                            ThreadPrint.class.notifyAll();
                        } else {
                            try {
                                ThreadPrint.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });


        t1.start();
        t2.start();
        t3.start();
    }

    private static int[] ans = new int[100];
    private static int index = 0;
    static {
        for (int i = 0; i < 100; i ++) {
            ans[i] = i + 1;
        }
    }



}
