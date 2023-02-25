package com.hangbo.javabase.bigdata;

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
		System.out.println(arr[(int)(target / 64) ] + " " + (1 << (target % 64)));
		return (arr[(int)(target / 64) ] & (1L << (target % 64))) != 0;
	}
	
	public void display(int row) {
		for (int i = 0; i < row; i ++ ) {
			long value = arr[i];
			List<Long> ans = new ArrayList<>();
			for (int j = 0; j < 64; j ++ ) {
				ans.add(value & 1);
				value >>= 1;
			}
			System.out.println("a[" + i + "]" + ans.toString());
		}
	}
	
	public static void main(String[] args) {
		BitLongMap bitLongMap = new BitLongMap();
		long[] arr = new long[]{64, 63, 62, 61};
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
		bitLongMap.display(3);
	}
}
