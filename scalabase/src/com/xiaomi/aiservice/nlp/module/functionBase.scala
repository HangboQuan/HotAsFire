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
}
