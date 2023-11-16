package com.alibaba.interview.bytedance;

/**
 * @author quanhangbo
 * @date 2023/11/16 16:49
 */
public class 字符串相加 {

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int add = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int ans = x + y + add;

            sb.append(ans % 10);
            add = ans / 10;
            i --;
            j --;
        }
        return sb.reverse().toString();
    }
}
