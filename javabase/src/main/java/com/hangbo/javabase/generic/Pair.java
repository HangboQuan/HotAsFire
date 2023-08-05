package com.hangbo.javabase.generic;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentMap;

/**
 * @author quanhangbo
 * @date 2023/8/5 18:08
 */
public class Pair<T> {

    // 一般来说 T U S 来代表任意类型 E代表集合元素类型 K,V
    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        this.first = newValue;
    }

    public void setSecond(T newValue) {
        this.second = newValue;
    }


}

class ArrayAlg {
    public static Pair<String> minmax(String[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        String min = a[0];
        String max = a[0];

        for (int i = 1; i < a.length; i ++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<>(min, max);
    }
}

class ArrayAlg1 {

    // 泛型类可以定义在泛型类中 也可定义在普通类
    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }
}


class ArrayAlg2 {

    // 类型变量的限定 怎么才能让所属的类有compareTo() => 将T限制为实现了Comparable接口的类
    // T应该是绑定(Comparable)的子类型 T和绑定类型可以是类, 也可以是接口 选择extends关键字是更接近子类的概念
    public static <T extends Comparable> T min(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }
        T smallest = a[0];
        for (int i = 0; i < a.length; i ++ ) {
            if (smallest.compareTo(a[i]) > 0) {
                smallest = a[i];
            }
        }
        return smallest;
    }
}
class PairTest1 {

    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
        Pair<String> mm = ArrayAlg.minmax(words);
        System.out.println("min = " + mm.getFirst() + ", max = " + mm.getSecond());


//        String middle = ArrayAlg1.<String>getMiddle("alibaba", "meituan", "pinduoduo");
        String middle = ArrayAlg1.getMiddle("alibaba", "meituan", "pinduoduo");



        //编译器会自动打包参数为Double和Integer,然后寻找这个类的共同超类型: extends Number implement Comparable接口, 其本身也是个泛型类
        //Number & Comparable<? extends Number & Comparable<?>>
//        double middle1 = ArrayAlg1.getMiddle(3.14, 1729, 0); 这会编译报错


    }
}

class ArrayAlg3 {
    public static <T extends Comparable> Pair<T> minmax(T[] a) {
        if (a == null || a.length == 0) {
            return null;
        }

        T min = a[0];
        T max = a[0];

        for (int i = 1; i < a.length; i ++) {
            if (min.compareTo(a[i]) > 0) {
                min = a[i];
            }
            if (max.compareTo(a[i]) < 0) {
                max = a[i];
            }
        }
        return new Pair<>(min, max);
    }
}

class PairTest2 {

    public static void main(String[] args) {
        LocalDate[] birthdays = {
                LocalDate.of(1910, 6, 22),
                LocalDate.of(1989, 6, 4),
                LocalDate.of(1976, 9, 9),
                LocalDate.of(1999, 12, 29),
        };
        Pair<LocalDate> mm = ArrayAlg3.minmax(birthdays);
        System.out.println("min = " + mm.getFirst() + ", max = " + mm.getSecond());

    }
}