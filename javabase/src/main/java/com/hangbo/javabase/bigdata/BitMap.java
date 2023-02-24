package com.hangbo.javabase.bigdata;

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
		arr[value / 32] |= 1 << (value % 32);
	}
	
	public boolean exist(int target) {
		return (arr[target / 32] & (1 << (target % 32))) != 1;
	}
	
	public void display() {
	
	}
	
	public static void main(String[] args) {
		int[] nums = {43, 56, 1999, 21474836, 489};
		BitMap bitMap = new BitMap();
		for (int i = 0; i < nums.length; i ++ ) {
			bitMap.addValue(nums[i]);
		}
		
		
		System.out.println(bitMap.exist(2023));
		System.out.println(bitMap.exist(1998));
		System.out.println(bitMap.exist(1999));
		System.out.println(bitMap.exist(489));
	}
	
	
}
