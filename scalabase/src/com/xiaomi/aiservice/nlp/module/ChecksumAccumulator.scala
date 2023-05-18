package com.xiaomi.aiservice.nlp.module

import scala.collection.mutable

/**
 * Description:
 * Author: quanhangbo
 * Date: 2023/5/18 21:49
 */
class ChecksumAccumulator {
    // 字段和方法 统称为成员  字段保留了对象的状态/数据  方法用这些数据来对对象执行计算
    var sum = 0
}

class ChecksumAccumulatorA {
    // 将字段标记为private来防止外部 直接访问字段 私有字段只能被定义在同一个类中的方法访问
    // scala中的public权限是默认的，不用显式指出
    private var sum = 0

    // 如果声明了私有变量，必须要通过方法提供其他类访问的接口，否则这个类没任何作用
    // 传参中的比那辆都是val而不是var, 原因是val更容易推敲，不需要像var进一步推敲是否被重新赋值过
    def add(b : Byte) : Unit = {
//        b = 1 报错
        sum += b
    }

    def checksum() : Int = {
        // return是多余的
        return ~(sum & 0xFF) + 1
    }

    // 甚至可以写为 scala会自动推算结果类型 每个方法即每个值
//    def add(b : Byte) = sum += b
//    def checksum() = ~(sum & 0xFF) + 1

    // 更好的做法 对类中声明为公有的方法显式地给出结果类型，更易读
//    def add(b : Byte): Unit = {sum += b}
//    def checksum(): Int = ~(sum & 0xFF) + 1
}

// scala不允许有static静态成员 因此有了单例对象singleton object
// 单例对象和某个类共用一个名字时，成这个类为伴生对象 必须在同一源码文件中指定伴生对象和伴生类
// 类和它的伴生对象可以互相访问对方的私有成员 伴生对象一般就理解为java static代码
// 单例对象不接受参数，因为其无法被new无法被实例化
object ChecksumAccumulatorA {
    private val cache = mutable.Map.empty[String, Int]
    def calculate(s: String) : Int = {
        if (cache.contains(s)) {
            cache(s)
        } else {
            val acc = new ChecksumAccumulatorA
            for (c <- s) {
                acc.add(c.toByte)
            }
            val cs = acc.checksum()
            // Map 添加key value
            cache += (s -> cs)
            cs
        }
    }

}

// 没有同名的伴生类的单例对象 => 孤立对象 用途：工具方法类 应用入口等
// scala源码文件隐式引用了java.lang和scala包 以及predef的单例对象 predef包含println之类的
object ChecksumAccumulatorB {

}


def main(args: Array[String]): Unit = {

    // 实例化
    val acc = new ChecksumAccumulator
    val csa = new ChecksumAccumulator

    // sum被定义为var, 而不是val 因此可以在后续代码中对其重新赋予不同的Int值
    acc.sum = 3

    // visit singleton object
    ChecksumAccumulatorA.calculate("alibaba")

//    acc = new ChecksumAccumulator 不能编译 acc是一个val

}

