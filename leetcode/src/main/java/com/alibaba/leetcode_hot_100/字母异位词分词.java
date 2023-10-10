package com.alibaba.leetcode_hot_100;

import java.util.*;

/**
 * @author quanhangbo
 * @date 2023/10/10 21:44
 */
public class 字母异位词分词 {

    public static List<List<String>> groupAnagrams(String[] strs) {
        List<String> res = new ArrayList<>();
        List<List<String>> s = new ArrayList<>();
        if (strs.length == 0) {
            return s;
        }
        if (strs.length == 1) {
            res.add(strs[0]);
            s.add(res);
            return s;
        }
        // 统计一个string个字符的个数
        HashMap<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i ++ ) {
            String str = strs[i];
            int[] tag = new int[26];
            for (int j = 0; j < str.length(); j ++ ) {
                tag[(str.charAt(j) - 'a')] ++;
            }
            String target = "";
            for (int k = 0; k < tag.length; k ++ ) {
                target += "" + (char)(k + 'a') + tag[k];
            }
            List<String> list = map.getOrDefault(target, new ArrayList<>());
            list.add(str);
            map.put(target, list);
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
//        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{"bdddddddddd", "bbbbbbbbbbc"}));
    }
}
