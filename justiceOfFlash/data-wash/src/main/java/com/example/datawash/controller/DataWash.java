package com.example.datawash.controller;

import com.example.datawash.dao.DataWashDao;
import com.example.datawash.utils.ReadExcelUtil;
import com.example.datawash.utils.WriteToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

/**
 *
 * @date 2019-12-9 16:33
 */

@Controller
public class DataWash {

    public void test() {
        try {
            ReadExcelUtil readExcelUtil = new ReadExcelUtil("C:\\Users\\颐信科技\\Desktop\\数据清洗\\整理.xlsx");
            String[] excelTitle = readExcelUtil.readExcelTitle();

            // 读取excel表格的title之后 ,把 title转成 数据字典里的 title
            for (String s : excelTitle) {
                if("备注".equals(s)){
                    s="123";
                }
                System.out.println("源表的标题列表: "+s);
            }

//            Map<Integer, Map<String, Object>> map = readExcelUtil.readExcelContent(excelTitle);
//
//            String filePath="C:\\Users\\颐信科技\\Desktop\\数据清洗\\info.xlsx";
//            String sheetName="100";
//            String[] title={"交易卡号","交易帐号","交易户名"};
//            new WriteToExcel().writeMapToExcel(filePath,title,map,sheetName);


        } catch (Exception e) {

        }

    }

    @Autowired
    private DataWashDao dataWashDao;

    public void test2(){

        List examine = dataWashDao.examine();
        System.out.println("---"+examine);
    }

}
