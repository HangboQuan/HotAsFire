package com.alibaba.topic.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author quanhangbo
 * @date 23-11-13 下午7:54
 */
public class 求一个整数的惩罚数 {
    static List<List<String>> arr = new ArrayList<>();
    public static int punishmentNumber(int n) {
        int count = 0;
        for (int i = 1; i <= n; i ++ ) {
            dfs(String.valueOf(i * i), 0, new ArrayList<>());

            for (List<String> s : arr) {
                int num = 0;
                for (String str : s) {
                    num += Integer.parseInt(str);
                }
                if (num == i) {
                    count += i * i;
                    break;
                }
            }
            arr = new ArrayList<>();
        }
        return count;
    }
    public static void dfs(String ans, int start, List<String> tmp) {

        if (start == ans.length()) {
            arr.add(new ArrayList<>(tmp));
        }
        for (int i = start; i < ans.length(); i ++ ) {
            String res = ans.substring(start, i + 1);
            tmp.add(res);
            dfs(ans, i + 1, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        punishmentNumber(500);
    }
}