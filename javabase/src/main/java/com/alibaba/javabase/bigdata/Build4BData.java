package com.alibaba.javabase.bigdata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;
import java.io.*;

/**
 * @author quanhangbo
 * @date 2024/3/12 10:41
 */
public class Build4BData {

    public static void main(String[] args) {
//        build4BNumberLists();
        totalNumberLists();
    }

    public static void build4BNumberLists() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:/a.txt"));
            int maxValue = 2147483647;
            for (long i = 0; i < 4000000000L; i ++ ) {
                int value = new Random().nextInt(maxValue) + 1;
                if (i % 1000000 == 0 ) {
                    System.out.println("遍历到：" + i);
                }
                bw.write(value + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void totalNumberLists() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:/a.txt"));
            String line;
            long count = 0;


            while((line = br.readLine()) != null) {
                System.out.println(line);
                count ++;
            }
            System.out.println("total number of numbers in this file is:" + count);
        } catch (Exception e) {

        }

    }
}
