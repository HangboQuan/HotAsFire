package com.alibaba.javabase.utils;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtils {

    public static List<String> cutWordByJieba(String query) {
        List<String> ans = new ArrayList<>();
        JiebaSegmenter segmenter = new JiebaSegmenter();
        List<SegToken> result = segmenter.process(query, JiebaSegmenter.SegMode.INDEX);
        for (SegToken token : result) {
            ans.add(token.word);
        }
        return ans;
    }

    public static List<String> cutWordByIKAnalyzer(String query) {
        try {
            List<String> ans = new ArrayList<>();
            StringReader reader = new StringReader(query);
            IKSegmenter ikSegmenter = new IKSegmenter(reader, true);
            Lexeme lexeme;
            while ((lexeme = ikSegmenter.next()) != null) {
                ans.add(lexeme.getLexemeText());
            }
            return ans;
        } catch (Exception e) {
            System.out.println("ik分词失败");
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        System.out.println(cutWordByJieba("大日本帝国于1937年7月7日，全面入侵中华民国"));
        System.out.println(cutWordByIKAnalyzer("Dennis Ritchie is an American computer scientist"));
        System.out.println(cutWordByIKAnalyzer("丹尼斯里奇是一位美国计算机科学家"));
        System.out.println(cutWordByJieba("丹尼斯里奇是一位美国计算机科学家"));
    }

}
