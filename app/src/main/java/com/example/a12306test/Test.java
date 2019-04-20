package com.example.a12306test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 昭阳 on 2019/4/17.
 */
public class Test {
    public static void main(String[] args) {
        String test = "passpo545=2417638b395d4f239efb495808235a99t5467; Path=/passport";
        String xy_zhenfgze="(\\w|\\_)+\\b";
        String zhengre = "(\\w|\\=)+;";
        Pattern compile = Pattern.compile(zhengre);
        Matcher matcher = compile.matcher(test);
        while (matcher.find())
        {
            System.out.println(matcher.group());
        }


    }


}
