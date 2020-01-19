package com.example.zijinchuantoupingtai.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.util
 * @date 2019/12/9 10:07
 * @Copyright
 */
public class ReadCsvUtil {

    public static void writeCsv(String[] headers, List<String[]> data, String filePath) throws IOException {

        //初始化csvformat
        CSVFormat formator = CSVFormat.DEFAULT.withRecordSeparator(null);

        //创建FileWriter对象
        FileWriter fileWriter=new FileWriter(filePath);

        //创建CSVPrinter对象
        try (CSVPrinter printer = new CSVPrinter(fileWriter, formator)) {
            //写入列头数据
            printer.printRecord(headers);
            if (null != data) {
                //循环写入数据
                for (String[] lineData : data) {

                    printer.printRecord(lineData);
                }
            }
        }catch (Exception e){

        }

        System.out.println("CSV文件创建成功,文件路径:"+filePath);

    }

    public static void main(String[] args) {
        String[] headers = {"名字","年龄","出生地"};
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"小明","22","重庆"});
        data.add(new String[]{"小红","18","南充"});
        data.add(new String[]{"小强","20","成都"});
        try {
            writeCsv(headers, data, "E:/text.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
