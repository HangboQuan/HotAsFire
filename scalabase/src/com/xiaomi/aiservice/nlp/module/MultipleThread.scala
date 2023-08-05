package com.xiaomi.aiservice.nlp.module

import play.api.libs.ws._
import play.api.libs.ws.ahc.AhcWSClient
import scala.concurrent.Future

/**
 * Description: 
 * Author: quanhangbo
 * Date: 2023/5/24 23:07
 */
object MultipleThread {

    def main(args: Array[String]): Unit = {

        //        val future: Future[String] = Future {
        //            Thread.sleep(2000)
        //            "Hello, Future"
        //        }.map { result =>
        //            println(result)
        //        }.recover {
        //            case ex: Exception =>
        //                println("throws Exception")
        //        }


        //        implicit val ws: WSClient = AhcWSClient()
        //        ws.url("https://www.baidu.com")
        // 惰性求值
        // 1. 仅仅运行和当前计算相关的任务，稍后执行其他任务，被推迟的任务的结果是不被需要的 => 节省本次运行花费在
        // 不必要资源的资源 用于提高效率和即使响应
        lazy val input = 10;
        val ex = expensiveComputation()
        if (input >= 10 && ex) {
            println("exec this method when use lazy, expensiveComputation not exex")
        }

    }

    def expensiveComputation(): Unit = {
        println("test lazy computation...")
    }

}
