package com.example.zijinchuantoupingtai.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.zijinchuantoupingtai.util.ReadJsonUtil;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.controller
 * @date 2019/11/19 11:03
 * @Copyright
 */
public class ReadJson {
    @Data
    static class KeyValue {
        private String name;
        private String zjlx_name;
        private String concat;
    }


    public static void main(String[] args) throws Exception {
        String reg = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
          "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

        String path = ReadJsonUtil.class.getClassLoader().getResource("shenfenzheng.json").getPath();
        String s = ReadJsonUtil.readJsonFile(path);
        System.out.println(s);

        List<KeyValue> keyValues = JSONObject.parseArray(s, KeyValue.class);
        Iterator<KeyValue> iterator = keyValues.iterator();
        while (iterator.hasNext()) {

            KeyValue next = iterator.next();
            boolean b = Pattern.compile(reg).matcher(next.concat).find();
            if (!b) {
                System.out.println(next.concat);
            }
        }

    }
}
