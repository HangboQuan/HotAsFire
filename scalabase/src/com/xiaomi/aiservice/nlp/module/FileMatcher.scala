package com.xiaomi.aiservice.nlp.module

import java.io.File

object FileMatcher {
    private def filesHeres = new File(".").listFiles
    def filesEnding(query: String) = {
        for(file <- filesHeres if file.getName.endsWith(query))
            yield file
    }
    def filesContaining(query: String) = {
        for(file <- filesHeres if file.getName.contains(query))
            yield file
    }
    def fileRegexing(query: String) = {
        for (file <- filesHeres if file.getName.matches(query))
            yield file
    }

    // 上述几个全是重复代码
    def filesMatching(query: String, matcher: (String, String) => Boolean) = {
        for (file <- filesHeres; if matcher(file.getName, query))
            yield file
    }
    // _.endsWith(_) 等价于 (fileName: String, query: String) => fileName.endsWith(query) 等价于 (fileName, query) => fileName.endsWith(query)
    def filesEnding1(query: String) = filesMatching(query, _.endsWith(_))
    def filesContaining1(query: String) = filesMatching(query, _.contains(_))
    def fileRegexing1(query: String) = filesMatching(query, _.matches(_))

    // 高阶函数的另一个重要作用是将高阶函数本身放在api中让调用方代码更加精简 List Set Map的api
    def containsNeg(nums: List[Int]) = {
        var exist = false
        for (num <- nums) {
           if (num < 0) exist = true
        }
        exist
    }

    def containsNeg1(nums: List[Int]) = nums.exists((num: Int) => num < 0)
    def containsNeg2(nums: List[Int]) = nums.exists(_ < 0)
    def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)

    def main(args: Array[String]): Unit = {
        println(containsNeg2(List(1, 2, 1, 22)))
        println(containsNeg1(List(1, 2, -1, 22)))
    }


    def plainOldSum(x: Int, y: Int) = x + y
    // 柯里化后的：一个参数列表->多个参数列表 将一个接受多个参数的函数转为一系列接受单个参数的函数 这些函数形成一个函数链 每个函数接受一个参数并返回一个函数 直到最后一个函数返回最终结果
    // 第一个参数x 是普通的函数参数 第二个参数y则是通过curriedSum返回的函数的参数
    def curriedSum(x: Int)(y: Int) = x + y
}
