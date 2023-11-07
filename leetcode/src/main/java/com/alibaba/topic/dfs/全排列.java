package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/11/7 20:01
 */
public class 全排列 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        List<Integer> ans = new ArrayList<>();
        dfs(nums, vis, ans);
        return res;
    }

    public void dfs(int[] nums, boolean[] vis, List<Integer> ans) {
        if (ans.size() == nums.length) {
            res.add(new ArrayList<>(ans));
            return ;
        }
        for (int i = 0; i < nums.length; i ++ ) {
            if (!vis[i]) {
                ans.add(nums[i]);
                vis[i] = true;
                dfs(nums, vis, ans);
                vis[i] = false;
                ans.remove(ans.size() - 1);
            }
        }
    }


}
