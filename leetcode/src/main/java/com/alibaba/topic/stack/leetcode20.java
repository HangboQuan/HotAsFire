package com.alibaba.topic.stack;

import java.util.Stack;

/**
 * @author quanhangbo
 * @date 2023/8/31 22:51
 */
public class leetcode20 {

    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++ ) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                stack.push(s.charAt(i));
            } else {
                if (!stack.empty()) {
                    Character c = stack.pop();

                }
            }
        }
        if (stack.empty()) {
            return true;
        }
        return false;
    }
}
