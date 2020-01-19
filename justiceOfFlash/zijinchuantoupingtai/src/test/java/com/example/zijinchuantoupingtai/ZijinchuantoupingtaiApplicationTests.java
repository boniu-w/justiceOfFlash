package com.example.zijinchuantoupingtai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class ZijinchuantoupingtaiApplicationTests {

    /**
     * 身份证
     */
    @Test
    void test1() {
        String shenfenzheng="410184198506200639";
        String reg="(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
          "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        boolean b = Pattern.compile(reg).matcher(shenfenzheng).find();
        System.out.println("与身份证 比较匹配---"+b);

    }

    /**
     *
     * 统一社会信用代码（18位）
     * 参照标准：
     * 		《GB_32100-2015_法人和其他组织统一社会信用代码编码规则.》
     * 		按照编码规则:
     * 		统一代码为18位，统一代码由十八位的数字或大写英文字母（不适用I、O、Z、S、V）组成，由五个部分组成：
     * 		第一部分（第1位）为登记管理部门代码，9表示工商部门；(数字或大写英文字母)
     * 		第二部分（第2位）为机构类别代码;(数字或大写英文字母)
     * 		第三部分（第3-8位）为登记管理机关行政区划码；(数字)
     * 		第四部分（第9-17位）为全国组织机构代码；(数字或大写英文字母)
     * 		第五部分（第18位）为校验码(数字或大写英文字母)
     */
    @Test
    void test2(){
        String shenfenzheng="410184198506200639";

        String xinyongdaima="92370113MA3HJP4T2C";
        String regex = "^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9ABCDEFGHJKLMNPQRTUWXY])$";

        boolean b = Pattern.compile(regex).matcher(xinyongdaima).find();
        System.out.println("与 统一社会信用代码 比较匹配---"+b);

        boolean b1 = Pattern.compile(regex).matcher(shenfenzheng).find();
        System.out.println(b1);
    }

    /**
     *
     * 组织机构 代码
     */
    @Test
    void test3(){

        String zuzhijigoudaima="MA3HJP4T2";
        String regex = "[0-9ABCDEFGHJKLMNPQRTUWXY]{9}";

        boolean b = Pattern.compile(regex).matcher(zuzhijigoudaima).find();
        System.out.println("与 组织机构 代码 比较匹配---"+b);
    }

}
