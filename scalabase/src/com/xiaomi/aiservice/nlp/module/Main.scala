package com.xiaomi.aiservice.nlp.module

import java.sql.{Connection, ResultSet}

/**
  * @author quanhangbo
  * @date 23-5-23 下午4:38
  */
class Main {

}


object Main{
    // implicit用法:
    /**
      * 1. 通过隐式转换 可以为现有方法添加额外的方法和操作 而无需修改原始类型的定义
      * 2.
      * @param str
      */
    implicit class StringExtension(str: String) {
        def toIntOption: Option[Int] = {
            try {
                Some(str.toInt)
            } catch {
                case _: NumberFormatException => None
            }
        }
    }

    def main(args: Array[String]): Unit = {
        val numberString = "123"
        val numberResult = numberString.toIntOption
        println(numberResult)

//        implicit val connection: Connection = new D("localhost", 3306)

    }

    def query(sql: String)(implicit connection: Connection): ResultSet = {
        // 执行查询操作
        println("exec select operation!")
        null
    }
}
//@Inject()注解的作用是: @Autowired WsClient ws, 其后面可以跟多个参数列表 都是以来参数中的参数 进行依赖注入
//@Singleton
//class HttpDomainClient @Inject()(implicit ws: WsClient) {
//
//}


