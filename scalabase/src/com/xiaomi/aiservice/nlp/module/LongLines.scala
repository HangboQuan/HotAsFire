package com.xiaomi.aiservice.nlp.module

import scala.io.Source

/**
 * @author quanhangbo
 * @date 23-5-19 下午7:32
 */
object LongLines {
    def processFile(filename: String, width: Int) = {
        val source = Source.fromFile(filename)
        for (line <- source.getLines()) {
            processLine(filename, width, line)
        }
    }

    private def processLine(filename: String, width: Int, line: String): Unit = {
        if (line.length > width) {
            println(filename + ":" + line.trim)
        }
    }
}
