package com.sankuai.meituan;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.*;


/**
 * @author quanhangbo
 * @date 2023/12/3 23:19
 */
public class generateExam {

    // 通过接入redis的zset来实现
    public static void main(String[] args) throws Exception {
        // 随机生成30-50面试 + 1~2到算法题

        ClassPathResource resource = new ClassPathResource("/excellent/question.txt");
        InputStream inputStream = resource.getInputStream();
        List<String> questions = IOUtils.readLines(inputStream, "utf-8");
        List<String> questionsRes = new ArrayList<String>();

        for (String str : questions) {
            String[] s = str.split("\\t");
            questionsRes.add(s[0]);
        }

        Collections.shuffle(questionsRes, new Random());
        List<String> res = new ArrayList<String>(questionsRes.subList(0, 50));


        ClassPathResource resource1 = new ClassPathResource("/excellent/leetcode.txt");
        InputStream inputStream1 = resource1.getInputStream();
        List<String> algorthms = IOUtils.readLines(inputStream1, "utf-8");
        List<String> algorthmsRes = new ArrayList<String>();
        for (String str : algorthms) {
            String[] s = str.split("\\t");
            algorthmsRes.add(s[0]);
        }

        Collections.shuffle(algorthmsRes, new Random());
        res.addAll(algorthmsRes.subList(0, 2));

        for (String s : res) {
            System.out.println(s);
        }
    }
}
