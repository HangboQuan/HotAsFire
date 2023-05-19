package com.xiaomi.aiservice.nlp.module

import java.io._
import java.net.URL
import scala.util.control.Breaks._


/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/18 22:30
 */
object ScalaBaseDataTypeAndOperation {

    def main(args: Array[String]): Unit = {
        // 使用"""" """来表示需要转义的特殊字符
        println("""Welcome to alibaba. Type "HELP" for help.""")
        println(
            """|Welcome to alibaba.
               |Type "HELP" for help.""".stripMargin)
        val name = "reader"
        println(s"hello, ${name}")
        println("hello," + name)
        println(s"this answer is ${6 + 7}")


        var filename = "default.txt"
        if (!args.isEmpty) {
            filename = args(0)
        }

//        var filename = if (!args.isEmpty) args(0) else "default.txt" 两个if实现的是相同的效果
//        val filesHere = (new File(".")).listFiles
//        for (file <- filesHere) {
//            println(file)
//        }

        // <=
        for (i <- 1 to 4) {
            println(i)
        }
        // <
        for (i <- 1 until 4) {
            println(i)
        }
//        scala  中并不常见
//        for (i <- 0 until filesHere.length - 1) {
//            println(filesHeres(i))
//        }

//        通过filter过滤
//        for (file <- filesHere if file.getName.endsWith(".scala")) {
//            println(file)
//        }

        val arr = Array(1, 2, 3, 4, 5)
        arr.foreach {
            element =>
                println(element)
        }

        // 数组下标是圆括号 而非方括号
        for (i <- 0 until arr.length) {
            println(arr(i))
        }

        println("------------------------------------")

        /**
          * 2
          * 1
          * 1
          * Today is sunny1
          */
        println(f())
        println(f1())
        println(f2())
        println(f3())
        println(urlFor("https://www.openai.com"))

        processList(List(1, 2, 3, 4))
        // 和Java不同的是: Java的try-catch-finally 并不返回某个值
        // 当finally子句包含显式返回语句或者抛出异常时，返回值会改写try-catch的值  如果没有显式的返回值就沿用try-catch的值
        def f(): Int = try return 1 finally return 2
        def f1(): Int = try 1 finally 2
        def f2(): Int = try return 1 finally 2
        def f3(): Int = try return 1 finally print("Today is sunny"); 2
        def urlFor(path: String): URL = {
            try {
                new URL(path)
            } catch {
                case ex: Exception => {
                    new URL("https://www.google.com/default.html")
                }
            }
        }

        def processList(list: List[Int]): Unit = {
            list match {
                case Nil => println("Empty list")
                case head :: tail => {
                    println(s"$head")
//                    println(tail)
                    tail.foreach(println)
                }
            }
        }


        // 多个<-子句
        def fileLines(file: File) = {
            scala.io.Source.fromFile(file).getLines().toList
        }

        //    def grep(pattern: String) = {
        //        for(
        //            file <- filesHere
        //            if file.getName.endsWith(".scala");
        //            line <- fileLines(file)
        //            if line.trim.matches(pattern)
        //        ) println(file + ":"  + line.trim)
        //    }

        def ans = for (i <- 1 to 10 if i % 2 == 0) yield i
        // List(2, 4, 6, 8, 10)
        println(ans)

        val n = 10
        // 抛出异常这个表达式的类型是Nothing
        val half = if (n % 2 == 0) n / 2 else throw new RuntimeException("n must be even")


        def getFile() = {
            val f = new FileReader("input.txt")
            try {

            } catch {
                case ex: FileNotFoundException =>
                case ex: IOException =>
            } finally {
                f.close()
            }

        }

        // Scala中的任何常量 字符串都可以作为样例 并且break语句是隐含的 Java的switch case只局限于整型 枚举 字符串常量
        def fruitList = List("apple", "banana", "orange", "chop")
        val fruit = if (fruitList.size > 0) fruitList(0) else ""
        val fruitResult = fruit match {
            case "apple" => "apple"
            case "fruit" => "fruit"
            case _ => "huh?"
        }
        println(fruitResult)


        /**
          * public String findEndwithScala(List<String> ans) {
          * int i = 0;
          * String result = null;
          * while (i < ans.size()) {
          * if (ans.get(i).startsWith("-")) {
          * continue;
          * }
          * if (ans.get(i).endsWith(".scala")) {
          * result = ans.get(i);
          * break;
          * }
          * i ++;
          * }
          * return result;
          * }
          *
          * scala 中是没有continue和break
          * @param s
          * @return
          */
        def continueBreak(s : List[String]): String = {
            var i = 0
            var findSucc = false
            // 为什么result 这里不能赋值为null Nil None => result = s(i)会报错？
            var result = ""
            while (i < s.length && !findSucc) {
                if (!s(i).startsWith("-")) {
                    if (s(i).endsWith(".scala")) {
                        result = s(i)
                        findSucc = true
                    }
                }
                i += 1
            }
            result
        }

        // 递归写法 实际scala的编译器并不会生成递归函数，因为函数调用都出现在函数尾部，生成于while循环类似的代码
        def searchNumIndex(i: Int, s: List[String]): Int = {
            if (i >= s.length) return -1
            if (s(i).endsWith(".scala")) return i
            searchNumIndex(i + 1, s)
        }

        val template = List("A.scala", "B.java", "C.go", "D.cpp")
        val result = if (searchNumIndex(0, template) < 0) null else template(searchNumIndex(0, template))
        println(result)


        // 第三方库中提供了break的方法
        def aresBreakAble(): Unit = {
            val in = new BufferedReader(new InputStreamReader(System.in))
            breakable {
                while(true) {
                    println("? ")
                    if (in.readLine() == "") break
                }
            }
        }

        /**
          * scala 结果为 2 1 就近原则
          */
        def scope(): Unit = {
            val a = 1;
            {
//                a = 2 编译失败
                val a = 2
                println(a)
            }
            println(a)
        }
        scope()

        /*
        这段和上述scala的结果不一样这里的结果2,2  这里花括号里的a不能重复定义，否则会报错
        public static void main(String[] args) {
            int a = 1;
            {
                a = 2;
                System.out.println(a);
            }
            System.out.println(a);
        }*/


        def makeRowSeq(row: Int) = {
            for (col <- 1 until 10) yield {
                val value = (row * col).toString
                val padding = 4 - value.length
                value + padding
            }
        }

        // mkString和""有什么区别
        def makeRow(row: Int) = makeRowSeq(row).mkString
        def makeRowCol() = {
            val tableSeq = for (i <- 1 until 10) yield makeRow(i)
            tableSeq.mkString("\n")
        }
        makeRowCol()
    }


}


