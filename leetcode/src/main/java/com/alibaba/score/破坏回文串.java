package com.alibaba.score;

/**
 * @author quanhangbo
 * @date 23-11-14 上午10:29
 */
public class 破坏回文串 {

    public String breakPalindrome(String palindrome) {
        char[] ch = palindrome.toCharArray();
        if (ch.length <= 1) {
            return "";
        }
        for (int i = 0; i < ch.length; i ++ ) {
            char tmp = ch[i];
            if (ch[i] != 'a') {
                ch[i] = 'a';
                if (isPalindRome(new String(ch))) {
                    return new String(ch);
                } else {
                    ch[i] = tmp;
                }
            }
        }
        return "a" + palindrome.substring(1);
    }


    public boolean isPalindRome(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}