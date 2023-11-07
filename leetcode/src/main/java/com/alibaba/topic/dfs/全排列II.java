package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2023/11/7 20:48
 */
public class 全排列II {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] vis = new boolean[nums.length];
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, vis, ans);
        return res;
    }

    public void dfs(int[] nums, boolean[] vis, List<Integer> ans) {
        if (ans.size() == nums.length) {
            res.add(new ArrayList<>(ans));
            return ;
        }
        for (int i = 0; i < nums.length; i ++ ) {
            /**
             * 思考题: 2023.11.7最开始写的时候，这里的判断条件写为if(i >= 1 && nums[i - 1] == nums[i])无法得出正确结果
             * 请问为什么？
             */
            if (vis[i] || (i >= 1 && nums[i - 1] == nums[i] && vis[i - 1])) {
                continue;
            }
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
