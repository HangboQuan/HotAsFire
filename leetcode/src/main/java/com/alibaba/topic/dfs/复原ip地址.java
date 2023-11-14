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

    List<String> res = new ArrayList<>();
    int[] seg = new int[4];
    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }

    public void dfs(String s, int level, int start) {
        if (level == 4) {
            if (start == s.length()) {
                StringBuilder ans = new StringBuilder();
                for (int i = 0; i < seg.length; i ++ ) {
                    ans.append(seg[i]);
                    if (i != seg.length - 1) {
                        ans.append(".");
                    }
                }
                res.add(ans.toString());
            }
            return ;
        }

        if (start == s.length()) {
            return ;
        }

        if (s.charAt(start) == '0') {
            seg[level] = 0;
            dfs(s, level + 1, start + 1);
            return ;
        }

        int addr = 0;
        for (int i = start; i < s.length(); i ++ ) {
            addr = addr * 10 + (s.charAt(i) - '0');
            if (addr >= 0 && addr <= 0xFF) {
                seg[level] = addr;
                dfs(s, level + 1, i + 1);
            } else {
                break;
            }
        }
    }
}
