package com.sankuai.meituan.algorithm;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author quanhangbo
 * @date 2023/12/1 22:44
 */
public class exam {

    public static void main(String[] args) throws Exception {
//        ClassPathResource resource = new ClassPathResource("excellent/question.txt");
//        InputStream inputStream = resource.getInputStream();
//        List<String> lists = IOUtils.readLines(inputStream, "utf-8");
//        for (String str : lists) {
//            Pattern pattern = Pattern.compile(".*?([0-9]+)次.*");
//            Matcher matcher = pattern.matcher(str);
//            if (matcher.find()) {
//                System.out.println(matcher.group(1));
//            } else {
//                System.out.println(str);
//            }
//        }
//        int[] ans = {-2,1,3,4,-1,2,1,-5,4};
//        System.out.println(longestSub(ans));

//        System.out.println(caculate("11+2*3+4"));
        int[] arr = {3, 2, 5, 8, 4, 7, 6};
        swapArrElement(arr);

        for (int v : arr) {
            System.out.println(v);
        }
    }


//    public class SlidingWindowRateLimiter {
//        private Jedis jedis;
//        private String key;
//        private int limit;
//
//        public void warnning(String key) {
//            long currentTime = System.currentTimeMillis();
//            long windowStart = currentTime - 60 * 1000;
//            jedis.zremrangeByScore(key, "-inf", String.valueOf(windowStart));
//
//            jedis.zadd(key, currentTime, String.valueOf(currentTime));
//            long currentExceptionRequests = jedis.zcard(key);
//            boolean flag = false;
//            if (currentExceptionRequests > limit) {
//                // 飞书发送告警通知
//                flag = true;
//            }
//            if (currentExceptionRequests == 0 && flag) {
//                // 飞书发送告警恢复通知
//                flag = false;
//            }
//        }
//    }


    // [-2, 1, 3, 4, -1, 2, 1, -5, 4] // 10
    public static int longestSub(int[] arr) {
        if (arr == null) {
            return -1;
        }
        if (arr.length <= 1) {
            return arr[0];
        }
        int pre = arr[0];
        int ans = arr[0];
        for (int i = 1; i < arr.length; i ++ ) {
            pre = Math.max(pre + arr[i], arr[i]);
            ans = Math.max(pre, ans);
        }
        return ans;
    }


















    // 11+2*3+4
    public static int caculate(String str) {
        int res = 0;
        List<Integer> nums = new ArrayList<>();
        List<Character> ch = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < str.length(); i ++ ) {
            if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/') {
                nums.add(Integer.valueOf(str.substring(j, i)));
                ch.add(str.charAt(i));
                j = i + 1;
            }
        }

        for (int num : nums) {
            System.out.println(num);
        }
        boolean[] bool = new boolean[nums.size()];
        for (int i = 0; i < ch.size(); i ++ ) {
            if (ch.get(i) == '*') {
                if (!bool[i]) {
                    int temp = nums.get(i) * nums.get(i + 1);
                    res += temp;
                    nums.set(i, temp);
                    bool[i + 1] = true;
                }
            }
            if (ch.get(i) == '/') {
                int temp = nums.get(i) / nums.get(i + 1);
                res += temp;
                nums.set(i, temp);
                bool[i + 1] = true;
            }
        }

        for (int i = 0; i < ch.size(); i ++ ) {
            if (!bool[i]) {
                if (ch.get(i) == '+') {
                    int temp = nums.get(i) + nums.get(i + 1);
                    res += temp;
                    nums.set(i, temp);
                    bool[i + 1] = true;
                }
                if (ch.get(i) == '-') {
                    int temp = nums.get(i) - nums.get(i + 1);
                    res += temp;
                    nums.set(i, temp);
                    bool[i + 1] = true;
                }
            }
        }
        return res;

    }


    // arr = [3, 2, 5, 8, 4, 7, 6]
    public static void swapArrElement(int[] arr) {
        if (arr == null) {
            System.out.println("----");
            return ;
        }
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if ((arr[i] & 1) == 1) {
                i ++;
            }

            if ((arr[j] & 1) == 0) {
                j --;
            }
            swap(arr, i, j);
        }
    }

    public static void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }



    // String "ab cd efg" => "abcdefg  "
    public static String moveSpaceToTail(String str) {
        char[] ch = new char[str.length()];
        for (int i = 0; i < ch.length; i ++ ) {
            if (ch[i] == ' ') {
                for (int j = i + 1; j < ch.length - 1; j ++ ) {
                    ch[j] = ch[j + 1];
                }
                ch[ch.length - 1] = ' ';
            }
        }
        return new String(ch);
    }


}
