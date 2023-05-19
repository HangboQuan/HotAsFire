package com.xiaomi.aiservice.nlp.module;

/**
 * @author quanhangbo
 * @date 23-5-19 下午3:09
 */
public class Baseline {

	public static void main(String[] args) {
		int a = 1;
		{
			a = 2;
			System.out.println(a);
		}
		System.out.println(a);

		nineLevelMultiple();
	}

	public static void nineLevelMultiple() {
		for (int i = 1; i <= 9; i ++ ) {
			for (int j = 1; j <= 9; j ++ ) {
				System.out.print(i * j + "\t");
			}
			System.out.println();
		}
	}


}
