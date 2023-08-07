package com.hangbo.javabase.reflection;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Random;

/**
 * @author quanhangbo
 * @date 2023/8/6 20:47
 */
public class ReflectionDemo {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // 反射是指程序运行期间发现更多类及其属性的能力
        /**
         * 反射机制可以：
         * 1. 在运行时分析类的能力
         * 2. 在运行时查看对象 如：编写一个toString方法供所有类使用
         * 3. 实现通用的数组操作代码
         * 4. 利用Method对象 很像C++中的函数指针
         *
         * Java运行时系统始终为所有对象维护一个运行时类型标识。这个信息跟踪着每个对象所属的类, 虚拟机利用运行时类型信息选择相应的方法执行
         *
         */

        Employee e = new Employee("quanhangbo", "Java Developer", 2322, 'm', 24, "Beijing");
        Class c1 = e.getClass();
        System.out.println(c1.getName() + " " + e.getName());

        Random generator = new Random();
        Class c2 = generator.getClass();
        System.out.println(c2.getName()); // java.util.Random

        // Java中的类如果不显示指定构造方法，由编译器自动生成一个无参的默认构造方法
        // newInstance方法调用默认的构造器初始化新对象 这个类没有默认的构造器 就会抛出一个异常
        e.getClass().newInstance();
    }

    @Data
    @Accessors(chain = true)
    static public class Employee {
        private String name;
        private String position;
        private double salary;
        private char sex;
        private int age;
        private String address;

        public Employee() {

        }

        public  Employee(String name, String position, double salary, char sex, int age, String address) {
            this.name = name;
            this.position = position;
            this.salary = salary;
            this.sex = sex;
            this.age = age;
            this.address = address;
        }

    }
}





