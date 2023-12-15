package com.alibaba.javabase.bigdata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/2/25 17:16
 */
public class BitLongMap {
	
	private long maxSize;
	private long arr[];
	
	public BitLongMap() {
		maxSize = 4000000000L;
		int length = (int)(maxSize / 64) + 1;
		arr = new long[length];
	}
	
	// a[value / 64] |= 1 << (value % 64);
	public void add(long value) {
		arr[(int)(value / 64)] |= 1L << (value % 64);
	}
	
	public boolean exist(long target) {
//		System.out.println(arr[(int)(target / 64) ] + " " + (1 << (target % 64)));
		return (arr[(int)(target / 64) ] & (1L << (target % 64))) != 0;
	}
	
	public void display(int row) {
		for (int i = 0; i < row; i ++ ) {
			long value = arr[i];
			if (value != 0) {
				List<Long> ans = new ArrayList<>();
				for (int j = 0; j < 64; j ++ ) {
					ans.add(value & 1);
					value >>= 1;
				}
				System.out.println("a[" + i + "]" + ans.toString());
			}
			
		}
	}
	
	// 61 62 63 64 128 198 1111 1999 19999 2147483647 2147483648 3999999999 4000000000
	public void sort(long max) {
		int row = (int)(max / 64L) + 1;
		for (int i = 0; i < row; i ++ ) {
			long value = arr[i];
			for (int j = 0; j < 64; j ++ ) {
				if ((value & (1L << j)) != 0) {
					System.out.print(i * 64L + j + " ");
				}
			}
		}
		System.out.println();
	}
	
	// 4B 不重复的正整数数中求出中位数
	public void middleNumber() {
		int row = (int)(maxSize / 64L) + 1;
		long count = 0;
		for (int i = 0; i < row; i ++ ) {
			long value = arr[i];
			for (int j = 0; j < 64; j ++ ) {
				if ((value & (1L << j)) != 0) {
					count ++;
				}
			}
		}
		System.out.println(count);
		long index = 0;
		// even
		long leftValue = 0, middleValue = 0, rightValue = 0;
		// index = count / 2, count / 2 - 1 求和 / 2
		for (int i = 0; i < row; i ++ ) {
			long value = arr[i];
			for (int j = 0; j < 64; j ++ ) {
				
				if ((value & (1L << j)) != 0) {
					++ index;
					if (index - 1 == count / 2 - 1) {
						leftValue = i * 64L + j;
					}
					if (index - 1 == count / 2) {
						rightValue = i * 64L + j;
						middleValue = rightValue;
					}
					
					if (index > count / 2) {
						break;
					}
				}
			}
		}
		
		if ((count & 1) == 0) {
			// 不过这里还是要考虑要爆long
			System.out.println((leftValue + rightValue) / 2);
		} else {
			System.out.println(middleValue);
		}
		
	}
	
	
	public static void main(String[] args) {
		BitLongMap bitLongMap = new BitLongMap();
//		long[] arr = new long[]{64, 63, 62, 61, 128, 198, 1111, 19999, 2147483647, 2147483648L, 1999, 3999999999L, 4000000000L};
//		long[] arr = new long[]{64, 63, 62, 61, 128, 198, 1111, 1222, 19999, 2147483647, 2147483648L, 1999, 3999999999L, 4000000000L};
//		long[] arr = new long[]{64, 32, 198};
//		long[] arr = new long[]{4000000000L, 3999999999L, 3999999998L};
		
		long[] arr = new long[]{4000000000L, 3999999999L, 3999999998L, 3999999997L};
		for (int i = 0; i < arr.length; i ++ ) {
			bitLongMap.add(arr[i]);
		}
		
		System.out.println(bitLongMap.exist(65));
		System.out.println(bitLongMap.exist(64));
		System.out.println(bitLongMap.exist(63));
		System.out.println(bitLongMap.exist(60));
		System.out.println(bitLongMap.exist(55));
		System.out.println(bitLongMap.exist(32));
		
		System.out.println(1L << 63);
		System.out.println(1 << 31);
		bitLongMap.display((int)(4000000000L / 64) + 1);
		bitLongMap.sort(4000000000L);
		bitLongMap.middleNumber();
	}
}
