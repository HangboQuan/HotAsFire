package com.hangbo.javabase.generic;

/**
 * @author quanhangbo
 * @date 2023/8/5 16:56
 */
public class GenericDemo01 {

    public static void main(String[] args) {
        /**
         * 1. 为什么要使用泛型？
         * 使用泛型编写的代码比杂乱使用Object变量，然后在强制类型转换的代码有更好的安全性和可读性，可以被很多不同类型的对象重用
         *
         * 增加泛型类之前 ArrayList类只维护一个Object引用的数组
         * public class ArrayList {
         *  private Object[] elementData;
         *  ...
         *  public Object get(int i) {...}
         *  public void add(Object o) {...}
         *
         * }
         *  i.获取一个值的时候必须强制类型转换 ArrayList files = new ArrayList(); String filename = (String)files.get(0);
         *  ii.添加一个值的时候，没有错误检查，可以想列表添加任何类型对象，这样编译和运行都不会报错，然而在读取值时强制转换类型就会失败
         *
         * JDK7 之后可以省略泛型的类型，类型可以从变量类型推断得出
         * 通过指定泛型类型，可以知道返回类型T, 在插入元素的时候编译器可以进行检查, 插入不同类型元素编译器会无法编译通过
         *
         * 泛型类可以看作诗普通类的工厂
         */


    }
}
