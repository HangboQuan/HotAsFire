package com.sankuai.meituan.algorithm;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
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
        ClassPathResource resource = new ClassPathResource("excellent/question.txt");
        InputStream inputStream = resource.getInputStream();
        List<String> lists = IOUtils.readLines(inputStream, "utf-8");
        for (String str : lists) {
            Pattern pattern = Pattern.compile(".*?([0-9]+)次.*");
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
            } else {
                System.out.println(str);
            }
        }
        int[] ans = {-2,1,3,4,-1,2,1,-5,4};
        System.out.println(longestSub(ans));
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
}
