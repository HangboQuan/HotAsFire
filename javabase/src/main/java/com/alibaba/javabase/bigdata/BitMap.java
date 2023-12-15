package com.alibaba.javabase.bigdata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/2/24 17:52
 */
public class BitMap {
	
	private int maxSize;
	private int[] arr;
	
	public BitMap() {
		this.maxSize = 2000000000;
		arr = new int[maxSize / 32 + 1];
	}
	
	// arr[i] 中的bits 有32bit，因此首先定位到arr[i]，然后再将第x位set进去
	// 32bit中， 比如整数31：a[0]中的index为31，整数32：a[1]中的index为0
	public void addValue(int value) {
//		arr[value / 32] |= (1 << (value % 32));
		arr[value / 32] = arr[value / 32] | (1 << value % 32);
//		System.out.println("first: " + arr[value / 32] + " " + value);
		
		
	}
	
	public boolean exist(int target) {
//		System.out.println(" pre:" + arr[target / 32] + " after:" + (1 << (target & 0x1F)));
//		System.out.println("pre:" + arr[target / 32] + " after:" + (1 << (target % 32)) + " result :" + (arr[target / 32] & (1 << (target % 32))) + " " + target);
//		return ((arr[target / 32]) & (1 << (target % 32))) == 1;
//		return ((arr[target / 32]) & (1 << (target % 32))) != 0;
		return (arr[target >> 5] & ( 1 << (target & 0x1F))) != 0;
	}
	
	public void display(int row) {
		for (int i = 0; i < row; i ++ ){
			List<Integer> list = new ArrayList<>();
			int temp = arr[i];
			for (int j = 0; j < 32; j ++ ) {
				list.add(temp & 1);
				temp >>= 1;
			}
			System.out.println("a[" + i + "]" + list);
		}
	}
	
	public static void fillCode() {
		int target = -2147483648;
		for (int i = target; i < target + 5; i ++ ) {
			int value = i;
			for (int j = 0; j < 32; j ++ ) {
				System.out.print(value & 1);
				value >>= 1;
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
//		int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 31, 32, 64, 192, 256, 43, 56, 1999, 24836, 489};
		int[] nums = {32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		BitMap bitMap = new BitMap();
		
//		for (int i = 0; i < nums.length; i ++ ) {
//			bitMap.addValue(nums[i]);
//		}
//		System.out.println(bitMap.exist(65));
//		System.out.println(bitMap.exist(64));
//		System.out.println(bitMap.exist(31));
//		System.out.println(bitMap.exist(30));
//		System.out.println(bitMap.exist(29));
//		bitMap.display(10);
		fillCode();
		
	}
	
	
}
