package com.alibaba.score;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.FileHandler;

/**
 * @author quanhangbo
 * @date 2025-03-07 10:37
 */
public class CodeDemo {

    public static void main(String[] args) throws Exception {
        /**
         * 实现模拟程序：读取文档中的手机号，给每个手机号推送一条短信
         * 给定文档：c:\phones.txt  格式为一行一个手机号，文档大小5GB
         *
         * 要求：1.尽可能快的完成推送，2.控制内存占用不得超过500MB（可以不用那么精确）
         */

        // 将整个类拷贝到你的开发环境，在此实现你的代码


        String path = "";
        FileHelper fileHelper = new FileHelper(path);
        ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i ++) {
            threadPoolExecutor.submit(
                    new Runnable() {
                        @Override
                        public void run() {
                            while (fileHelper.readLine() != null) {
                                String line = fileHelper.readLine();
                                SmsSender smsSender = new SmsSender();
                                String context = "";
                                smsSender.send(context, line);
                            }
                        }
                    }
            );
        }


        threadPoolExecutor.shutdown();
    }

/////////////////////////////以下内容可以当工具来用，不需要实现//////////////////////////////////

    /**
     * 文件辅助类，内部管理读取细节和资源，顺序读取文件
     */
    public static class FileHelper {
        /**
         * 构造方法
         * @param path 文件路径
         */
        public FileHelper(String path) {
            // 无需实现....

        }

        /**
         * 读取一行数据
         * @return 每次调用返回下一行数据，读到文件结尾返回null
         */
        public String readLine() {
            // 无需实现...
            return null;
        }

        /**
         * 关闭文件
         */
        public void close() {
            // 无需实现...
        }
    }

    /**
     * 发送短信类封装，内部封装rpc调用，同步发送，单次调用平均耗时500毫秒
     */
    public static class SmsSender {
        /**
         * 发送短信
         * @param content 内容
         * @param phone 手机号
         */
        public void send(String content, String phone) {
            // 无需实现...
        }
    }
}


