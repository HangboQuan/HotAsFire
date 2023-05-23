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
        //        val f = (_: Int) + (_: Int)

        def sum(a: Int, b: Int, c: Int) = a + b + c
        // 实例化一个接收三个整数作为参数的函数值，并指向这个新的函数值的引用赋值给变量a
        val a = sum _
        println(a(1, 2, 3))

        // 闭包
        val more = 1
        val addMore = (x: Int) => x + more
        println(addMore(10))

        // 闭包：如果一个函数访问到了它的外部变量的值，那么这个函数和他所处的环境成为闭包
        def f1() = {
            val a: Int = 10
            // f2即是一个嵌套函数也是一个闭包 在f1内部定义接受一个参数b并返回a + b结果 f2依赖于f1中的局部变量a 因此这是一个闭包
            def f2(b: Int) = {
                a + b
            }
            // f2 _是将函数f2 转为换数值 f1返回的就是一个函数值 也就是闭包
            f2 _
        }
        val f = f1()
        println(f(3))
        // 函数柯里化: 把一个参数列表的多个参数，变成多个参数列表
        // 目的： 将复杂的参数逻辑简单化，函数柯里化一定存在闭包
        println(f1()(3))

        def f2() = {
            val b = 10
            //            val r = def f3(c: Int) = {
            //                b + c
            //            }
            //            r 这样写会发生报错
        }

        // 函数字面量和匿名函数是一个东西
        // 自由变量： 闭包可以访问作用域之外的变量，这些变量即自由变量 自由变量的值在闭包创建是被捕获 并在以后的调用中保持不变
        // multiplier的返回值是一个匿名函数
        def multiplier(factor: Int) = (x: Int) => x * factor
        val multiplyByTwo = multiplier(2)
        // multiplyByTwo 现在就是一个函数值 即闭包 它在执行时会将传入的参数与之前定义的factor相乘得到结果
        println(multiplyByTwo(4))
        // 闭包引用透明性： 意味着闭包的行为只取决于输入 不依赖外部环境的其他状态

        // 高阶函数可以接受其他函数作为参数 或者返回函数作为结果 结合闭包 创建更灵活的高阶函数
        def operateNumbers(numbers: List[Int], operation: Int => Int): List[Int] = {
            numbers.map(operation)
        }
        val numbers = List(1, 2, 3, 4, 5)
        val squaredNumbers = operateNumbers(numbers, (x: Int) => x * x)
        // List(1, 4, 9, 16, 25)
        println(squaredNumbers)

        var counter = 0
        val incrementCounter = () => {
            counter += 1
            counter
        }
        // 1
        // 2
        // 闭包的副作用：闭包访问和修改其作用域之外的变量时，可能会导致副作用 副作用：对外部状态的改变 可能导致程序行为变得不可预测
        println(incrementCounter())
        println(incrementCounter())

        // 闭包还可以用于延迟计算
        def delayCalculation(x: Int, y: Int) = () => x + y
        val calc = delayCalculation(3, 4)
        // <function0>
        // 7
        println(calc)
        println(calc())

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
