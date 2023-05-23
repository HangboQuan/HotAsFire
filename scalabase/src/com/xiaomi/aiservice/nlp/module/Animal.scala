package com.xiaomi.aiservice.nlp.module

/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/21 23:01
 */
abstract class Animal {
    // 抽象类 是一个不能被实例化的类 包含抽象方法、具体方法、字段 抽象方法只在抽象类中声明 没有具体实现
    def sound(): Unit // 抽象方法 没有具体实现
    def eat(): Unit = {
        println("Eating......") // 具体方法
    }
}

// 继承：一个类继承另一个类的特征 子类可以重写父类的方法 或者添加新的方法
class Dog extends Animal {
    override def sound(): Unit = {
        println("Bark!")
    }
}

class Cat extends Animal {
    override def sound(): Unit = {
        println("Meow!")
    }
}

// 特质 类似于接口  特质
trait Swimmer {
    def swimmer(): Unit = {
        println("Swimming......")
    }
}

trait Fly {
    def fly(): Unit
}

class Fish extends Animal with Swimmer {
    override def sound(): Unit = {
        println("mou mou!")
    }
}

//  特质中的方法 既可以是有方法体的也可以是无方法体的 一个类只有单继承 但是可以实现多个特质(接口)
class Bird extends Animal with Swimmer with Fly {
    override def sound(): Unit = {
        println("jiji-zhazha")
    }

    override def fly(): Unit = {
        println("flying......")
    }
}

// case class是一种特殊的类 用于定义不可变的数据模型 使得在处理不可变的数据时更加方便和简介
// 1. 自动构造器，用于创建类的实例 2. 不可变性，一旦创建了实例 它的值不能被更改
case class Person(name: String, sex: Char, age: Int, Weight: Double)
object BaseClass {
    def main(args: Array[String]): Unit = {
        val person1 = Person("quanhangbo", 'm', 24, 89)
        // Person(quanhangbo,m,24,89.0)
        println(person1)
    }
}

class MyInt(val value: Int)

