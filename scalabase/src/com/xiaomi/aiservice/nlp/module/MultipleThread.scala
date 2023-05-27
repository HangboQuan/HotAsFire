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

        implicit val ws: WSClient = AhcWSClient()
        ws.url("https://www.baidu.com")
    }
    
}
