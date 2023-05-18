package com.xiaomi.aiservice.nlp.module

import java.io.{File, FileNotFoundException, FileReader, IOException}

/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/18 22:30
 */
class ScalaBaseDataTypeAndOperation {

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

}
