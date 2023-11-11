package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/11/11 21:29
 */
public class 分割回文串 {

    List<List<String>> res = new ArrayList<>();
    public List<List<String>> partition(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, 0, ans);
        return res;
    }

    public void dfs(String s, int start, List<String> ans) {
        if (start == s.length()) {
            res.add(new ArrayList<>(ans));
            return ;
        }

        // StringBuilder sb = new StringBuilder();
        // for (int end = start; end < s.length(); end ++ ) {
        //     sb = sb.append(s.charAt(end));
        //     if (isHalfEqual(sb.toString())) {
        //         ans.add(sb.toString());
        //         dfs(s, start + 1, ans);
        //         ans.remove(ans.size() - 1);
        //     }
        // }

        for (int i = start; i < s.length(); i ++ ) {
            String cur = s.substring(start, i + 1);
            if (isHalfEqual(cur)) {
                ans.add(cur);
                dfs(s, i + 1, ans);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public boolean isHalfEqual(String s) {
        if (s.length() == 1) return true;
        int half = s.length() / 2;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left ++;
            right --;
        }
        return true;
    }
}
