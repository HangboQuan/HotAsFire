package com.hangbo.javabase.jvm;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author quanhangbo
 * @date 2023/8/25 17:02
 */
public class reference {

    public static void main(String[] args) {
//        testStrongReference();
        testSoftReference();
//        testWeakReference();

        /**
         * PhantomReference： 虚引用不影响对象的生命周期，无法通过虚引用来取得一个对象实例
         */

    }

    public static void testStrongReference() {
        /**
         * 强引用：最常见的为Object obj = new Object()
         * 某个对象有强应用关联，则永远不会被回收，直到发生OOM
         * 中断的方法是obj = null
         */
        Student student = new Student();
        student = null;
        System.gc();
    }


    /**
     * [B@1b6d3586
     * [B@1b6d3586
     * null
     */
    public static void testSoftReference() {
        /**
         * 软引用：当内存不足时，JVM尝试一次gc，gc完成后还是内存不足的话，就会回收软引用
         * 应用场景：缓存
         */
        // 设置JVM最大堆内存为20M, 先创建10M大小的软引用对象
        SoftReference<byte[]> softReference = new SoftReference<>(new byte[1024 * 1024 * 10]);
        // 获取软引用的对象
        System.out.println(softReference.get());
        // gc一次, 有充足的空间, 并不回收对象, 获取软引用对象
        System.gc();
        System.out.println(softReference.get());

        // 创建12M大小的对象, 由于前面软引用占用了10M, 此时进行第一次gc, 第一次gc之后还是没有足够的空间, 因此回收软引用
        byte[] dances = new byte[1024 * 1024 * 12];
        System.out.println(softReference.get());
    }

    public static void testWeakReference() {
        /**
         * 虚引用：不管内存是否充足，发生gc的时候，一定会回收虚引用对象
         */
        WeakReference<byte[]> weakReference = new WeakReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(weakReference.get());

        System.gc();
        System.out.println(weakReference.get());


    }

    static public class Student {

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Student 被回收了");
        }
    }
}
