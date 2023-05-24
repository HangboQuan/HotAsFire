package com.xiaomi.aiservice.nlp.module

/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/24 22:21
 */
object Collections {
    def main(args: Array[String]): Unit = {
        val list = List(1, 2, 3)
        // map保留原始集合的结构 将集合元素实现转换 返回一个和原集合大小相同的新集合
        val doubleList = list.map(_ * 2)
        // List(2, 4, 6)
        println(doubleList)
        
        // flatMap可以改变集合的层级集合 展开为单层集合
        val nestedList = list.flatMap(x => List(x, x + 1))
        // List(1, 2, 2, 3, 3, 4)
        println(nestedList)
        
        val users: List[User] = List(
            User("Alice", 24, List("Reading", "Writing")),
            User("Bob", 25, List("Cooking", "Coding")),
            User("Tom", 3, List("Sports", "Coding"))
        )
        
        val names: List[String] = users.map(_.name)
        // List(Alice, Bob, Tom)
        println(names)
        
        val nextYearAge: List[Int] = users.map(_.age + 1)
        // List(25, 26, 4)
        println(nextYearAge)
        val userInterests: List[List[String]] = users.map(user => user.interests)
        val userInterests1: List[List[String]] = users.map(_.interests)
        // List(List(Reading, Writing), List(Cooking, Coding), List(Sports, Coding)) 列表嵌套
        println(userInterests)
        
        // List(Reading, Writing, Cooking, Coding, Sports, Coding)
        val allInterests: List[String] = users.flatMap(_.interests)
        println(allInterests)
        
        val allInterestsUpperCase: List[String] = users.flatMap(_.interests).map(_.toUpperCase())
        println(allInterestsUpperCase)
        
        val nameAndInterests: List[(String, String)] = users.flatMap(user => user.interests.map(interest => (user.name, interest)))
        println(nameAndInterests)
        
        
        val array = Array(1, 2, 3, 4)
        array.foreach(a => println(a))
        
        val set = Set(1, 2, 2, 2, 3, 3, 4)
        set.foreach(b => println(b))
        
        // 覆盖的
        val map = Map("quanhangbo" -> "alibaba", "quanhangbo" -> "bytedance", "quanhangbo" -> "pinduoduo")
        map.foreach {
            case(key, value) => {
                println(s"Key: $key, Value: $value")
            }
        }
        
        for ((key, value) <- map) {
            println(key + " " + value)
        }
        
        val unsort = List(3, 7, 2, 5, 1, 6, 8)
        // List(1, 2, 3, 5, 6, 7, 8)
        println(unsort.sorted)
        
        val tuple = (1, "hello", 3.14)
        println(tuple)
        
        val seq = Seq("google", "openai", 1)
        // List(google, openai, 1)
        println(seq)
    }
    case class User(name: String, age: Int, interests: List[String])
    
}