package com.example.zijinchuantoupingtai;

import com.example.zijinchuantoupingtai.controller.Demo;
import com.example.zijinchuantoupingtai.util.ReadExcelUtil;
import com.example.zijinchuantoupingtai.util.Regex;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai
 * @date 2019/11/20 17:02
 * @Copyright
 */
public class TestDemo {

    @Test
    void test() {
        String haoma = "911201166009036680";
        HashMap hashMapShenfenzheng = new HashMap(); // 身份证 map


        Regex regex = new Regex();
        String trim = haoma.trim();
        String replace = trim.replace("-", "");
        boolean is = regex.isLicense18(replace);
        if (is) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }


    @Test
    void test2(){
        Demo demo = new Demo();
        demo.validateHaoma2();

    }

    /**
     * 测试写入excel
     */
    @Test
    void test3(){
        Demo demo = new Demo();
        demo.validateHaoma2();
        demo.testWriteExcel();

    }

}
