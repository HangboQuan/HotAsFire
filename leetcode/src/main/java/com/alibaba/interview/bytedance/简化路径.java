package com.alibaba.interview.bytedance;


import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/11/16 17:08
 */
public class 简化路径 {

    // 自己写法 空间复杂度有点高
    public String simplifyPath1(String path) {
        String[] strs = path.split("\\/");
        Stack<String> stack = new Stack<>();
        for (String str : strs) {
            if (str.equals(".") || str.equals("")) {
                continue;
            }
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(str);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        List<String> ans = new ArrayList<>();
        while (!stack.isEmpty()) {
            ans.add("/" + stack.pop());
        }
        for (int i = ans.size() - 1; i >= 0; i -- ) {
            sb.append(ans.get(i));
        }
        return sb.toString();
    }

    // 双端队列
    public String simplifyPath(String path) {
        String[] strs = path.split("\\/");
        Deque<String> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if ("".equals(str) || ".".equals(str)) {
                continue;
            }
            if (str.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                stack.offerLast(str);
            }
        }

        if (stack.isEmpty()) {
            return "/";
        } else {
            while (!stack.isEmpty()) {
                sb.append("/").append(stack.pollFirst());
            }
        }
        return sb.toString();
    }
}
