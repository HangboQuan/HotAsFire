package com.xiaomi.aiservice.nlp.module

/**
  * @author quanhangbo
  * @date 23-5-19 下午5:28
  */
object functionBase {

    def main(args: Array[String]): Unit = {
        val res = makeRowCol()
        println(res)
        useYield()
        val result = operation(2, 3, add)
        println(result)

        val increase = (x: Int) => {
            println("we are here")
            x + 1
        }
        println(increase(10))

        val someNumbers = List(-4, -1, 0, 22, 23, 1)
        someNumbers.foreach((x: Int) => println(x))
        println(someNumbers.filter((x: Int) => x > 0))
        // 省略参数类型声明
        println(someNumbers.filter(x => x > 0))
        // 占位符 表示一个或多个参数 满足每个参数只在函数字面量中出现一次
        println(someNumbers.filter(_ > 0))

        // 下面两者是等价的 _可以替换单独的参数 也可以替换整个参数列表，作为整个参数列表的话实际是在编写一个部分应用的函数
        someNumbers.foreach(println _)
        someNumbers.foreach(x => println(x))

        // 第一个下划线=>第一个参数 第二个下划线=>第二个参数
        val f = (_: Int) + (_: Int)

        def sum(a: Int, b: Int, c: Int) = a + b + c
        // 实例化一个接收三个整数作为参数的函数值，并指向这个新的函数值的引用赋值给变量a
        val a = sum _
        println(a(1, 2, 3))

        // 闭包
        val more = 1
        val addMore = (x: Int) => x + more
        println(addMore(10))
    }

    def makeRowSeq(row: Int) = {
        for (col <- 1 until 10) yield {
            val value = (row * col).toString
            val padding = " " * (4 - value.length)
            value + padding
        }
    }

    // mkString和""有什么区别
    def makeRow(row: Int) = makeRowSeq(row).mkString

    def makeRowCol() = {
        val tableSeq = for (i <- 1 until 10) yield makeRow(i)
        tableSeq.mkString("\n")
    }

    // yield用法 使用yield生成新集合
    def useYield(): Unit = {
        val numbers = List(1, 2, 3, 4, 5)
        val doubleNumbers = for (num <- numbers) yield num * 2
        println(doubleNumbers)
        val evenNumbers = for (num <- numbers if num % 2 == 0) yield num
        println(evenNumbers)

        val words = List("hello", "world")
        val flattendedChars = for {
            word <- words
            char <- word
        } yield char
        // List(h, e, l, l, o, w, o, r, l, d)
        println(flattendedChars)


        val animals = List("cat", "dog", "elephant")
        val animalSounds = for {
            animal <- animals
            sound <- animal match {
                case "cat" => List("meow")
                case "dog" => List("woof")
                case "elephant" => List("trumpet")
            }
        } yield sound
        // List(meow, woof, trumpet)
        println(animalSounds)

        val numbers1 = List(1, 2, 3, 4)
        val combinations = for {
            x <- numbers1
            y <- numbers1

        } yield (x, y)
        // List((1,1), (1,2), (1,3), (1,4), (2,1), (2,2), (2,3), (2,4), (3,1), (3,2), (3,3), (3,4), (4,1), (4,2), (4,3), (4,4))
        println(combinations)
    }

    // 函数字面量 括号里是参数 右箭头 函数体
    // 函数字面量 是指可以作为值传递的函数 也即匿名函数或闭包 在代码中直接定义无需使用命名函数 => 提供了一种方便的方式定义和传递函数
    (x: Int, y: Int) => x + y
    val add: (Int, Int) => Int = (x: Int, y: Int) => x + y

    def operation(a: Int, b: Int, f: (Int, Int) => Int): Int = {
        f(a, b)
    }



}
