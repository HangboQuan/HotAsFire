package com.hangbo.javabase.bigdata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Random;

/**
 * @author quanhangbo
 * @date 2023/2/23 19:16
 */
public class FindTargetIn4B {

	public static void main(String[] args) throws Exception {
		
		// bitmap 怎么用？
		BufferedReader br = new BufferedReader(new FileReader("D:/a.txt"));
		String line;
		HashSet<Long> set = new HashSet<>();
		long total = 0;
		while((line = br.readLine()) != null) {
			set.add(Long.parseLong(line));
			total ++;
			if (total % 100000 == 0) {
				System.out.println(total);
			}
		}
		
		int target = new Random().nextInt(1000000);
		
		if (set.contains(target)) {
			System.out.println(target);
		} else {
			System.out.println("not found");
		}
	
	
	
	//        生成40亿long类型的整数
	//        BufferedWriter bw = new BufferedWriter(new FileWriter("aa.txt"));
	
	//        BufferedWriter bw = new BufferedWriter(new FileWriter("D:/a.txt"));
	//
	//        int maxValue = 2147483647;
	//        for (long i = 0; i < 4000000000L; i ++ ) {
	//            int value = new Random().nextInt(maxValue) + 1;
	//            System.out.println(value);
	//            if (i % 1000000 == 0 ) {
	//                System.out.println("遍历到：" + i);
	//            }
	//            bw.write(value + "\n");
	//        }
	}
}
