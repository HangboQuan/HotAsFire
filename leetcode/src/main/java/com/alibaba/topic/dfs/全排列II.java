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

    public int beautifulSubstrings(String s, int k) {

        int count = 0;
        for(int i = 0; i < s.length(); i ++ ) {
            for (int j = i + 1; j < s.length(); j ++ ) {
                String res = s.substring(i, j + 1);
                if (valid(res, k)) {
                    count ++;
                }
            }
        }
        return count;
    }

    public boolean valid(String str, int k) {
        int vCount = 0;
        int cCount = 0;
        for (int i = 0; i < str.length(); i ++ ) {
            if (str.charAt(i) == 'a' || str.charAt(i) == 'o' || str.charAt(i) == 'e' || str.charAt(i) == 'i' || str.charAt(i) == 'u') {
                vCount ++;
            } else {
                cCount ++;
            }
        }
        return vCount == cCount && (vCount * cCount) % k == 0;
    }

    public int beautifulSubstrings1(String s, int k) {

        int count = 0;
        StringBuilder v = new StringBuilder();
        for (int i = 0; i < s.length(); i ++ ) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'o' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'u') {
                v.append("1");
            } else {
                v.append("0");
            }
        }

        for (int i = 0; i < v.toString().length(); i ++ ) {
            for (int j = i + 1; j < v.toString().length(); j ++ ) {

            }
        }

        return count;
    }




    public static void main(String[] args) {
        System.out.println(binarySerach(new int[]{10, 14, 19, 26, 27, 31, 33, 35, 42, 44}, 33));
    }

    public static int binarySerach(int[] nums, int target){
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
