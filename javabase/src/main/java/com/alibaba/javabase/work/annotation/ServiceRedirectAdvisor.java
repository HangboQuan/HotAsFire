package com.alibaba.javabase.work.annotation;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author quanhangbo
 * @date 2024-05-25 16:11
 */
@Component
public class ServiceRedirectAdvisor extends AbstractPointcutAdvisor {
    private final StaticMethodMatcherPointcut pointCut = new StaticMethodMatcherPointcut() {

        @Override
        public boolean matches(Method method, Class<?> aClass) {
            return method.isAnnotationPresent(ServiceRedirect.class);
        }
    };

    public ServiceRedirectAdvisor() {

    }
    @Resource
    private ServiceRedirectInterceptor serviceRedirectInterceptor;

    @Override
    public Pointcut getPointcut() {
        return this.pointCut;
    }

    @Override
    public Advice getAdvice() {
        return this.serviceRedirectInterceptor;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int x = arr[i];
            while (i < j) {
                while (i < j && arr[j] > x) {
                    j --;
                }
                if (i < j) {
                    arr[i ++] = arr[j];
                }

                while (i < j && arr[i] < x) {
                    i ++;
                }
                if (i < j) {
                    arr[j --] = arr[i];
                }
                arr[i] = x;
            }
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }

    }
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 5, 8, 4, 7, 6};
        quickSort(arr, 0, 6);
        for (int v : arr) {
            System.out.println(v);
        }

        // 實現兩個線程交替打印
//        Thread threadA = new Thread(new ThreadA());
//        Thread threadB = new Thread(new ThreadB());
//        threadA.start();
//        threadB.start();

        // 實現三個線程交替打印

        Thread threadC = new Thread(new ThreadC());
        Thread threadD = new Thread(new ThreadD());
        Thread threadE = new Thread(new ThreadE());
        threadC.start();
        threadD.start();
        threadE.start();

    }


    private final static Object Lock = new Object();
    private static int value = 1;


    static class ThreadA implements Runnable {

        @Override
        public void run() {
            synchronized (Lock) {
                while (value <= 100) {
                    if (value % 2 == 1) {
                        System.out.println("ThreadA:" + value);
                        value ++;
                    }
                    Lock.notify();
                    try {
                        if (value <= 100) {
                            Lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            synchronized (Lock) {
                while (value <= 100) {
                    if (value % 2 == 0) {
                        System.out.println("ThreadB:" + value);
                        value ++;
                    }
                    Lock.notify();
                    try {
                        if (value <= 100) {
                            Lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
    }

    static class ThreadC implements Runnable {
        @Override
        public void run() {
            synchronized (Lock) {
                while (value <= 75) {
                    if (value % 3 == 1) {
                        System.out.println("ThreadC:" + value);
                        value ++;
                    }
                    Lock.notifyAll();
                    try {
                        if (value <= 75) {
                            Lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class ThreadD implements Runnable {
        @Override
        public void run() {
            synchronized (Lock) {
                while (value <= 75) {
                    if (value % 3 == 2) {
                        System.out.println("ThreadD:" + value);
                        value ++;
                    }
                    Lock.notifyAll();
                    try {
                        if (value <= 75) {
                            Lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    static class ThreadE implements Runnable {
        @Override
        public void run() {
            synchronized (Lock) {
                while (value <= 75) {
                    if (value % 3 == 0) {
                        System.out.println("ThreadE:" + value);
                        value ++;
                    }
                    Lock.notifyAll();
                    try {
                        if (value <= 75) {
                            Lock.wait();
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }


}
