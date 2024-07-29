package com.sankuai.meituan;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author quanhangbo
 * @date 2023/12/3 23:19
 */
public class generateExam {

    public static void text1() throws Exception {
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

    public static void text2() throws Exception {
        ClassPathResource resource = new ClassPathResource("/excellent/question.txt");
        InputStream inputStream = resource.getInputStream();
        List<String> questions = IOUtils.readLines(inputStream, "utf-8");
        List<String> questionsRes = new ArrayList<String>();
        List<Integer> questionsScore = new ArrayList<Integer>();

        int qCount = 0;
        for (String str : questions) {
            if (qCount == 0) {
                qCount ++;
                continue;
            }
            String[] s = str.split("\\t");
            questionsRes.add(s[0]);
            questionsScore.add(Integer.valueOf(s[1]));

        }

        List<Integer> questionsWeight = questionsScore;



        ClassPathResource resource1 = new ClassPathResource("/excellent/leetcode.txt");
        InputStream inputStream1 = resource1.getInputStream();
        List<String> algorithms = IOUtils.readLines(inputStream1, "utf-8");
        List<String> algorithmsRes = new ArrayList<String>();
        List<Integer> algorithmScore = new ArrayList<Integer>();

        int sCount = 0;
        for (String str : algorithms) {
            if (sCount == 0) {
                sCount ++;
                continue;
            }
            String[] s = str.split("\\t");
            algorithmsRes.add(s[0]);
            algorithmScore.add(Integer.valueOf(s[1]));
        }

        List<Integer> algorithmWeight = algorithmScore;


        List<String> res = new ArrayList<>();

        List<String> selectedQuestions = weightedRandomChoice(questionsRes, questionsWeight, 50, 80);
        List<String> selectedAlgorithms = weightedRandomChoice(algorithmsRes, algorithmWeight, 2, 3);
        res.addAll(selectedQuestions);
        res.addAll(selectedAlgorithms);

        for (String s : res) {
            System.out.println(s);
        }
    }


    // 通过接入redis的zset来实现
    public static void main(String[] args) throws Exception {
        // 随机生成30-50面试 + 1~2到算法题
//        text1();
        text2();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        HashMap hashMap = new HashMap();


    }

    public static List<String> weightedRandomChoice(List<String> keys, List<Integer> weights, int min, int max) {
        int totalWeight = weights.stream().mapToInt(Integer::intValue).sum();
        Random random = new Random();
        List<String> chosenKeys = new ArrayList<>();

        while (chosenKeys.size() < min || chosenKeys.size() > max) {
            double randomValue = random.nextDouble() * totalWeight;
            double cumulativeWeight = 0;

            for (int i = 0; i < keys.size(); i++) {
                cumulativeWeight += weights.get(i);
                if (cumulativeWeight >= randomValue) {
                    chosenKeys.add(keys.get(i));
                    break;
                }
            }
        }

        return chosenKeys;
    }







//    public static void main(String[] args) {
//        System.out.println(twoAddSum("123", "1234"));
//    }


    public static String twoAddSum(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int cp = 0;
        while (i >= 0 || j >= 0 || cp != 0) {
            int s1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int s2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            cp = s1 + s2 + cp;

            sb.append(cp % 10);
            cp /= 10;
            i --;
            j --;
        }
        return sb.reverse().toString();
    }



}
