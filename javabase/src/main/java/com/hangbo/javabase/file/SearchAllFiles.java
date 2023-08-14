package com.hangbo.javabase.file;

import java.io.File;

/**
 * @author quanhangbo
 * @date 2023/8/14 14:07
 */
public class SearchAllFiles {

    public static void main(String[] args) {
        String path = "E://books";
        File file = new File(path);
        File[] files = file.listFiles();
        for (File f : files) {
            // 遍历每一个文件夹
            printAllFiles(f);
        }
    }

    public static void printAllFiles(File f) {
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                printAllFiles(file);
            }
        }
        System.out.println(f.getName());


    }
}
