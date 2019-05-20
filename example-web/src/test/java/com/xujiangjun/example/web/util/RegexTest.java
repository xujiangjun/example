package com.xujiangjun.example.web.util;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xujiangjun
 * @date 2017-10-24 16:38
 */
public class RegexTest {

    @Test
    public void testReg(){
        Pattern pattern = Pattern.compile("(he)\\1(af)");
        Matcher matcher = pattern.matcher("heheafda");
        System.out.println(matcher.find());
        boolean matches = Pattern.matches("1\\d{10}$", "18770029115");
        System.out.println(matches);
    }
}
