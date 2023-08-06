package com.hangbo.javabase.generic;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;


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

    public T createInstance(T instance) {
        return instance;
    }

    public T createInstance(Class<T> clazz) throws InstantiationException, IllegalAccessException {
        return clazz.newInstance();
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
    //     public static <T extends Comparable<? super T>> T min(T[] a)
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
    // 类型擦除是在运行时不存在泛型类型的信息，保证兼容性
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

@Data
@Accessors
class Employee {
    private double bonus;
    private double salary;
    private String name;

    public Employee() {

    }

    public Employee(String name) {
        this.name = name;
    }

    public Employee(double bonus, double salary, String name) {
        this.bonus = bonus;
        this.salary = salary;
        this.name = name;
    }
}

@Data
@Accessors
class Manager extends Employee {

    public Manager() {
        super();
    }

    public Manager(String name) {
        super(name);
    }

    public Manager(double bonus, double salary, String name) {
        super(bonus, salary, name);
    }

}
class PairTest2 {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        /**
         * 泛型转换：
         * 1. 虚拟机中没有泛型，只有普通的类和方法
         * 2. 所有的类型参数都用他们的限定类型替换
         * 3. 桥方法被合成来保持多态
         * 4. 为保持类型安全性，必要时插入强制类型转换
         */
        LocalDate[] birthdays = {
                LocalDate.of(1910, 6, 22),
                LocalDate.of(1989, 6, 4),
                LocalDate.of(1976, 9, 9),
                LocalDate.of(1999, 12, 29),
        };
        Pair<LocalDate> mm = ArrayAlg3.minmax(birthdays);
        System.out.println("min = " + mm.getFirst() + ", max = " + mm.getSecond());


        // 1. 不能用基本类型实例化参数类型 Pair<double>, 原因是类型擦除后, Pair类含有Object类型的域
        // 2. 运行时类型查询只适用于原始类型 Pair<String>类型擦除后，变为原始类型Pair
        // 3. 不能创建参数化类型的数组
        Pair<String> a = new Pair<>();
//        if (a instanceof Pair<String>) {
//            // 报错 illegal generic type for instanceof
//        }
//
//        if (a instanceof  Pair<T>) {
//            // cannot resolve symbol T
//        }

        if (a instanceof Pair) { // 不报错
            System.out.println("a is Pair type");
        }

        // 原因是泛型和数组的设计上存在不兼容性，由于类型擦除在运行时的泛型的类型参数是未知的，但是数组在创建是必须指定元素的类型，因此Java不允许直接创建泛型数组
//        List<String>[] arrayOfList = new List<String>[10]; // 编译错误
        Pair<String>[] tables = (Pair<String>[]) new Pair<?>[10]; // 可以声明通配类型的数组，然后进行类型转换 但是结果可能不安全

        // 不能使用像new T(...),new T[...],T.class
//
//        Pair<String> pair = new Pair<>();
//        String instance = pair.createInstance("hello");
//
//        Pair<String> pair1 = new Pair<>();
//        String instance1 = pair1.createInstance(String.class);

        Pair<Employee> p = new Pair<>(new Employee("lusu"), new Employee("zhouyu"));
        Pair<Manager> p1 = new Pair<>(new Manager("lusu"), new Manager("zhouyu"));

        printBuddies(p);
        printBuddies(p1);
    }


    // Pair<? extends Employee> 表示任何泛型Pair类型, 它的类型参数是Employee的子类
    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();

        System.out.println(first.getName() + " and " + second.getName() + " are buddies.");

        /**
         * 只能读取值，不能更改值
         * ？ extends Employee getFirst(); 该方法没问题
         * void setFirst(? extends Employee); 不能调用该方法原因是只知道需要传递Employee的子类型，但是无法确定具体的类型
         */
        // 超类型限定 ? super Manager, 表示Manager的所有超类型 目的=> 可以更改值 但是不能读取值
        Pair<Object> p2 = new Pair<>();
        minmaxBonus(new Manager[]{new Manager(2322.01, 650.0, "quanhango"),
                new Manager(3557.73, 650.0, "yangming")}, p2);

    }
    // 超类型限定的通配符可以向泛型对象写入 子类型限定的通配符可以从泛型对象读取
    public static void minmaxBonus(Manager[] a, Pair<? super Manager> result) {
        if (a.length == 0) {
            return ;
        }
        Manager min = a[0];
        Manager max = a[0];

        for (int i = 1; i < a.length; i ++ ) {
            if (min.getBonus() > a[i].getBonus()) min = a[i];
            if (max.getBonus() < a[i].getBonus()) max = a[i];
        }
        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] a, Pair<? super Manager> result) {
        minmaxBonus(a, result);
        swapHelper(result);
    }

    // 无限定的通配符 类型Pair<?>有以下方法: ? getFirst(); void setFirst(?)
    // public static <T> boolean hasNulls(Pair<T> p) 等价于如下方法, 用来判断pair是否包含一个null引用
    public static boolean hasNulls(Pair<?> p) {
        return p.getFirst() == null || p.getSecond() == null;
    }

    public static void swap(Pair<?> p) {
        swapHelper(p);
    }

    public static <T> void swapHelper(Pair<T> p) {
        T t = p.getFirst();
        p.setFirst(p.getSecond());
        p.setSecond(t);
    }
}