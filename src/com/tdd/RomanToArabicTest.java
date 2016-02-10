package com.tdd;

import junit.framework.Assert;

import org.junit.Test;

public class RomanToArabicTest {

    @Test
    public void test_Roman_I_to_Arabic_1() {
        Roman roman=new Roman("I");
        Assert.assertEquals(1, roman.toArabic());
        StringBuffer buffer=new StringBuffer("#$##asdfj");
        System.out.println(buffer.codePointAt(0));
    }

}
