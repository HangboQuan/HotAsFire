package com.alibaba.javabase.bigdata;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author quanhangbo
 * @date 2023/2/23 19:16
 */
public class FindTargetIn4B {

	public static void main(String[] args) throws Exception {
		
		// bitmap 怎么用？
		long start = System.currentTimeMillis();
		BufferedReader br = new BufferedReader(new FileReader("D:/a.txt"));
		String line;
		BitLongMap map = new BitLongMap();
//		long target = 444338700L;
		long target = 2147483648L;
		long begin = System.currentTimeMillis();
		long count = 0;
		boolean flag = false;
/*		while((line = br.readLine()) != null) {
			long value = (Long.parseLong(line));
			map.add(value);
//			if (value == target) {
//				flag = true;
//				break;
//			}
//			count ++;
		}
		// 1814050403 1073747385L
		map.middleNumber();
		map.exist(1073747385L);*/
		/**
		 * count:3999999040
		 * load success:559777
		 * exist: true
		 * latency: 560000
		 */
//		System.out.println("count:" + count);
//		System.out.println("load success:" + (System.currentTimeMillis() - begin));
//		System.out.println("exist: " + map.exist(target));
//		System.out.println("latency: " + (System.currentTimeMillis() - start));
		
		System.out.println("count:" + count);
		System.out.println("load success:" + (System.currentTimeMillis() - begin));
		System.out.println("exist: " + (flag == true));
		System.out.println("latency: " + (System.currentTimeMillis() - start));

//		int target = new Random().nextInt(1000000);
//
//		if (set.contains(target)) {
//			System.out.println(target);
//		} else {
//			System.out.println("not found");
//		}
//
//		BitSet bit = new BitSet();
	
	
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
		totalNumberLists();
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
