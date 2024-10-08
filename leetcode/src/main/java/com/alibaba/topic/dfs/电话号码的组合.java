package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author quanhangbo
 * @date 2023/11/13 14:14
 */
public class 电话号码的组合 {

    String[] ans = {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };
    List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return res;
        }
        dfs(digits, 0, new StringBuilder());
        return res;
    }

    public void dfs(String digits, int level, StringBuilder sb) {
        if (level == digits.length()) {
            res.add(sb.toString());
            return ;
        }
        for (int i = 0; i < ans[digits.charAt(level) - '0'].length(); i ++ ) {
            sb.append(ans[digits.charAt(level) - '0'].charAt(i));
            dfs(digits, level + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations2(String digits) {
        HashMap<Integer, String> map = new HashMap<>();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        return null;
    }
}
