package com.xiaomi.aiservice.nlp.module

import scala.concurrent.Future

/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/24 23:07
 */
object MultipleThread {
    
    def main(args: Array[String]): Unit = {
        
        val future: Future[String] = Future {
            Thread.sleep(2000)
            "Hello, Future"
        }.map { result =>
            println(result)
        }.recover {
            case ex: Exception =>
                println("throws Exception")
        }
    }
    
}
