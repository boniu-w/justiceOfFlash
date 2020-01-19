package com.example.zijinchuantoupingtai;

import com.alibaba.fastjson.JSONObject;
import com.example.zijinchuantoupingtai.entity.Excel;
import com.example.zijinchuantoupingtai.util.ReadJsonUtil;
import com.example.zijinchuantoupingtai.util.Regex;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.sql.Array;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai
 * @date 2019/11/19 14:27
 * @Copyright
 */

public class TestJsonRenyuanxinxi {
    Regex regex = new Regex();

    String shenfenzhengRegex = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
      "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    String xinyongdaimaRegex = "^([159Y]{1})([1239]{1})([0-9ABCDEFGHJKLMNPQRTUWXY]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9ABCDEFGHJKLMNPQRTUWXY])$";

    String zuzhijigoudaimaRegex = "[0-9ABCDEFGHJKLMNPQRTUWXY]{9}";

    @Test
    void test() {
        String path = ReadJsonUtil.class.getClassLoader().getResource("info.json").getPath();
        String s = ReadJsonUtil.readJsonFile(path);
        List<Excel> excels = JSONObject.parseArray(s, Excel.class);
        Iterator<Excel> iterator = excels.iterator();
        while (iterator.hasNext()) {

            Excel next = iterator.next();
            String zjlx_name = next.getZjlx_name();


            switch (zjlx_name) {
                case "全国组织机构代码证书":
                    // 先执行一下 去横杠
//                    boolean b1 = Pattern.compile(zuzhijigoudaimaRegex).matcher(next.getConcat()).find();
                    boolean b1 = Pattern.compile(zuzhijigoudaimaRegex).matcher(next.getConcat().replace("-", "")).find();

                    if (!b1) {
                        System.out.println(next.getName() + "\t" + next.getZjlx_name() + "\t" + next.getConcat());
                    }
                    break;
                case "统一社会信用证":
                    boolean b2 = Pattern.compile(xinyongdaimaRegex).matcher(next.getConcat()).find();
                    if (!b2) {
                        System.out.println(next.getName() + "\t" + next.getZjlx_name() + "\t" + next.getConcat());
                    }
                    break;
                case "居民身份证":
                    boolean b3 = Pattern.compile(shenfenzhengRegex).matcher(next.getConcat()).find();
                    if (!b3) {
                        System.out.println(next.getName() + "\t" + next.getZjlx_name() + "\t" + next.getConcat());
                    }
                    break;
            }

        }
    }


    @Test
    void test2() {
        HashMap hashMap1 = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();

        String path = ReadJsonUtil.class.getClassLoader().getResource("info.json").getPath();
        String s = ReadJsonUtil.readJsonFile(path);
        List<Excel> excels = JSONObject.parseArray(s, Excel.class);
        Iterator<Excel> iterator = excels.iterator();

        while (iterator.hasNext()) {
            Excel next = iterator.next();
            boolean b1 = Pattern.compile(shenfenzhengRegex).matcher(next.getConcat().replace("-", "")).find();
            boolean b2 = Pattern.compile(zuzhijigoudaimaRegex).matcher(next.getConcat().replace("-", "")).find();
            boolean b3 = Pattern.compile(xinyongdaimaRegex).matcher(next.getConcat().replace("-", "")).find();


            if (b1) {
                hashMap1.put(next.getConcat(), next);
            }
            if (b2) {
                hashMap2.put(next.getConcat(), next);
            }
            if (b3) {
                hashMap3.put(next.getConcat(), next);
            }

        }
        Set entrySet = hashMap3.entrySet();
        for (Object o : entrySet) {
            System.out.println(o);
        }

        System.out.println("hashMap1.size()" + hashMap1.size());
        System.out.println("hashMap2.size()" + hashMap2.size());
        System.out.println("hashMap3.size()" + hashMap3.size());
    }

    @Test
    void test3() {
        HashMap hashMap1 = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();

        ArrayList<Excel> excelArrayList1 = new ArrayList<>();
        ArrayList<Excel> excelArrayList2 = new ArrayList<>();
        ArrayList<Excel> excelArrayList3 = new ArrayList<>();

        String path = ReadJsonUtil.class.getClassLoader().getResource("info.json").getPath();
        String s = ReadJsonUtil.readJsonFile(path);
        List<Excel> excels = JSONObject.parseArray(s, Excel.class);
        Iterator<Excel> iterator = excels.iterator();

        while (iterator.hasNext()) {
            Excel next = iterator.next();
            boolean b1 = Pattern.compile(shenfenzhengRegex).matcher(next.getConcat().replace("-", "")).find();
            boolean b2 = Pattern.compile(zuzhijigoudaimaRegex).matcher(next.getConcat().replace("-", "")).find();
            boolean b3 = Pattern.compile(xinyongdaimaRegex).matcher(next.getConcat().replace("-", "")).find();


            if (b1) {
                hashMap1.put(next.getConcat(), next);
                excelArrayList1.add(next);
            }
            if (b2) {
                hashMap2.put(next.getConcat(), next);
                excelArrayList2.add(next);
            }
            if (b3) {
                hashMap3.put(next.getConcat(), next);
                excelArrayList3.add(next);
            }

        }

        System.out.println("excelArrayList1.size()" + excelArrayList1.size());
        System.out.println("excelArrayList2.size()" + excelArrayList2.size());
        System.out.println("excelArrayList3.size()" + excelArrayList3.size());


        System.out.println("hashMap1.size()" + hashMap1.size());
        System.out.println("hashMap2.size()" + hashMap2.size());
        System.out.println("hashMap3.size()" + hashMap3.size());

    }


    @Test
    void test4() {

        HashMap hashMap1 = new HashMap(); // 身份证 map
        HashMap hashMap2 = new HashMap(); // 统一社会信用代码 map
        HashMap hashMap3 = new HashMap(); // 组织机构 map

        ArrayList<Excel> excelArrayList1 = new ArrayList<>(); // 身份证list
        ArrayList<Excel> excelArrayList2 = new ArrayList<>(); // 身份证list
        ArrayList<Excel> excelArrayList3 = new ArrayList<>(); // 组织机构代码 list

        String path = ReadJsonUtil.class.getClassLoader().getResource("info.json").getPath();
        String s = ReadJsonUtil.readJsonFile(path);
        List<Excel> excels = JSONObject.parseArray(s, Excel.class);
        Iterator<Excel> iterator = excels.iterator();

        while (iterator.hasNext()) {
            Excel next = iterator.next();
            boolean idNumber = regex.isIDNumber(next.getConcat().replace("-", ""));
            if (idNumber) {
                excelArrayList1.add(next);
                hashMap1.put(next.getConcat(), next);
            }

            boolean license18 = regex.isLicense18(next.getConcat().replace("-", ""));
            if (license18) {
                excelArrayList2.add(next);
                hashMap2.put(next.getConcat(), next);
            }

            boolean zuZhi = regex.isZuZhi(next.getConcat().replace("-", ""));
            if (zuZhi) {
                hashMap3.put(next.getConcat(), next);
            }
        }

        System.out.println("excelArrayList1.size()" + excelArrayList1.size());
        System.out.println("excelArrayList2.size()" + excelArrayList2.size());
        System.out.println("excelArrayList3.size()" + excelArrayList3.size());

        System.out.println(hashMap1.size());
        System.out.println(hashMap2.size());
        System.out.println(hashMap3.size());

        Set<Map.Entry<String, Excel>> entrySet = hashMap2.entrySet();
        for (Map.Entry<String, Excel> stringExcelEntry : entrySet) {
            System.out.println(stringExcelEntry.getKey() + "\t" + stringExcelEntry.getValue());
        }

        Set<Map.Entry<String, Excel>> zuzhiEntrySet = hashMap3.entrySet();
        for (Map.Entry<String, Excel> stringExcelEntry : zuzhiEntrySet) {
            System.out.println(stringExcelEntry.getKey() + "\t" + stringExcelEntry.getValue());

        }

    }


}
