package com.example.zijinchuantoupingtai.controller;

import com.example.zijinchuantoupingtai.util.ReadExcelUtil;
import com.example.zijinchuantoupingtai.util.Regex;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.controller
 * @date 2019/11/20 17:29
 * @Copyright
 */
public class Demo {

    public static HashMap hashMapShenfenzheng = new HashMap(); // 身份证 map
    public static HashMap hashMapTongyishehuixinyong = new HashMap(); // 统一社会信用代码 map
    public static HashMap hashMapZuzhijigou = new HashMap(); // 组织机构 map

    public static HashMap falseHashMapTongyishehuixinyong = new HashMap(); // false map


    public void validateHaoma2() {

//        ReadExcelUtil readExcelUtil = new ReadExcelUtil("C:\\Users\\颐信科技\\Desktop\\资金穿透平台\\info\\万条数据.xlsx");
        ReadExcelUtil readExcelUtil = new ReadExcelUtil("C:\\Users\\颐信科技\\Documents\\WXWork\\1688850904715042\\Cache\\File\\2019-12\\ewqewqe.csv");
        try {
            // 获取 第一行 -- 标题部分
            String[] strings = readExcelUtil.readExcelTitle();
            for (String string : strings) {
                System.out.println(string);
            }

            // 获取剩余 行 -- 内容部分
            Set<List> listSet = readExcelUtil.readExcelContentToSet();
            for (List list : listSet) {
                String s = (String) list.get(2);
                // 遍历 验证第三列 -- 号码列
                this.volidateHaoma(s, list);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        new Demo().validateHaoma2();
//    }

    /**
     * 验证 号码的 正确性 并 分成 几张表
     *
     * @param haoma 要校验的号码
     * @param list  整个表的list集合
     */
    public void volidateHaoma(String haoma, List<String> list) {
        Regex regex = new Regex();

        String trim = haoma.toUpperCase().trim();
        String replace = trim.replace("-", "");


        boolean idNumber = regex.isIDNumber(replace);
        boolean license18 = regex.isLicense(replace);
        boolean zuZhi = regex.isZuZhi(replace);

        // haoma 为键 整行为值
        if (idNumber) {
            hashMapShenfenzheng.put(trim, list);
        }

        if (license18) {
            hashMapTongyishehuixinyong.put(trim, list);
        }

        if (zuZhi) {
            hashMapZuzhijigou.put(trim, list);
        }

        if (!idNumber && !license18 && !zuZhi) {

            falseHashMapTongyishehuixinyong.put(trim, list);
        }


    }

    /**
     * 将List集合数据写入excel（单个sheet）
     *
     * @param filePath   文件路径
     * @param excelTitle 文件表头
     * @param map        要写入的数据集合
     * @param sheetName  sheet名称
     */
    public void writeMapToExcel(String filePath, String[] excelTitle, Map<String, List> map, String sheetName) {
//        System.out.println("开始写入文件>>>>>>>>>>>>");
        Workbook workbook = null;
        if (filePath.toLowerCase().endsWith("xls")) {//2003
            workbook = new HSSFWorkbook();
        } else if (filePath.toLowerCase().endsWith("xlsx")) {//2007
            workbook = new XSSFWorkbook();
        } else {
//			logger.debug("invalid file name,should be xls or xlsx");
        }
        //create sheet
        Sheet sheet = workbook.createSheet(sheetName);
        int rowIndex = 0;//标识位，用于标识sheet的行号
        //遍历数据集，将其写入excel中
        try {
            //写表头数据
            Row titleRow = sheet.createRow(rowIndex);
            for (int i = 0; i < excelTitle.length; i++) {
                //创建表头单元格,填值
                titleRow.createCell(i).setCellValue(excelTitle[i]);
            }
//            System.out.println("表头写入完成>>>>>>>>");

            rowIndex++;

            //循环写入主表数据
            Set<Map.Entry<String, List>> entries = map.entrySet();
            for (Map.Entry<String, List> entry : entries) {
                Row row = sheet.createRow(rowIndex);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue((String) entry.getValue().get(0));

                Cell cell1 = row.createCell(1);
                cell1.setCellValue((String) entry.getValue().get(1));

                Cell cell2 = row.createCell(2);
                cell2.setCellValue((String) entry.getValue().get(2));
                rowIndex++;
            }

//            System.out.println("主表数据写入完成>>>>>>>>");
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
            System.out.println(filePath + "\t--写入文件成功>>>>>>>>>>>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testWriteExcel() {
        // 要写入的文件路径
        String filePath = "C:\\Users\\颐信科技\\Desktop\\资金穿透平台\\info\\经过检验的统一社会信用代码信息.xlsx";
        // 表头 信息
        String[] strings = new String[3];
        strings[0] = "name";
        strings[1] = "name2";
        strings[2] = "haoma";
        // sheet 名
        String tongyishehuixinyongSheet = "正确的统一社会信用代码";
        writeMapToExcel(filePath, strings, hashMapTongyishehuixinyong, tongyishehuixinyongSheet);

        String falseInfoFilePath = "C:\\Users\\颐信科技\\Desktop\\资金穿透平台\\info\\经过检验的错误的信息.xlsx";
        String falseInfoSheet = "不正确的信息";
        writeMapToExcel(falseInfoFilePath, strings, falseHashMapTongyishehuixinyong, falseInfoSheet);

        String shenfenzhengFilePath = "C:\\Users\\颐信科技\\Desktop\\资金穿透平台\\info\\经过检验的身份证信息.xlsx";
        String shenfenzhengSheet = "正确的统一社会信用代码";
        writeMapToExcel(shenfenzhengFilePath, strings, hashMapShenfenzheng, shenfenzhengSheet);


        String zuzhijigouFilePath = "C:\\Users\\颐信科技\\Desktop\\资金穿透平台\\info\\经过检验的组织机构信息.xlsx";
        String zuzhijigouSheet = "正确的统一社会信用代码";
        writeMapToExcel(zuzhijigouFilePath, strings, hashMapZuzhijigou, zuzhijigouSheet);


    }

}
