package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/11/10 23:11
 */
public class 复原ip地址 {
/*
    未看题解情况下，答案错误
    List<String> res = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {

        dfs(s, 0, res, 0);
        return res;
    }
    public boolean isValid(int nums) {
        return nums >= 0 && nums <= 255;
    }

    public void dfs(String s, int step, List<String> res, int pre) {
        if (step == s.length() && res.size() == 4) {
            return ;
        }
        for (int i = step; i < s.length(); i ++ ) {
            int num = pre * 10 + (s.charAt(i) - '0');
            if (isValid(num)) {
                res.add(num + "");
            }
            dfs(s, i + 1, res, num);
            if (isValid(num)) {
                res.remove(res.size() - 1);
            }
        }
    }*/

    static List<String> res = new ArrayList<>();
    static int[] seg = new int[4];
    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }

    /**
     * 从s[start]位置开始搜索，搜索ip地址中的第id段，id∈[0,3]，枚举出当前这一段ip地址的所有可能情况，满足要求的话 就进行下一次调用
     * @param s
     * @param id
     * @param start
     */
    public static void dfs(String s, int id, int start) {

        if (id == 4) {
            if (start == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < seg.length; i ++ ) {
                    sb.append(seg[i]);
                    if (i != seg.length - 1) {
                        sb.append(".");
                    }
                }
                res.add(sb.toString());
            }
            return ;
        }

        if (start == s.length()) {
            return ;
        }

        if (s.charAt(start) == '0') {
            seg[id] = 0;
            dfs(s, id + 1, start + 1);
        }
        int addr = 0;
        for (int end = start; end < s.length(); end ++ ) {
            addr = addr * 10 + (s.charAt(end) - '0');
            if (addr >= 0 && addr <= 0xFF) {
                seg[id] = addr;
                dfs(s, id + 1, end + 1);
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("25525511135"));
    }
}
