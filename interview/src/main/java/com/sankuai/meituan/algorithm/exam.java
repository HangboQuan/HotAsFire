package com.sankuai.meituan.algorithm;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;
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

    }
}
