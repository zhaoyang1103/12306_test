package com.example.a12306test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 昭阳 on 2019/4/17.
 */
public class Test {
    public static void main(String[] args) {
        String test = "46,36,249,113,";
        String xy_zhenfgze="(\\w|,)+\\b";
        Pattern compile = Pattern.compile(xy_zhenfgze);
        Matcher matcher = compile.matcher(test);
        while (matcher.find())
        {
            System.out.println(matcher.group());
        }


    }


}
