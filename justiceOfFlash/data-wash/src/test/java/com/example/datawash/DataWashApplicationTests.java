package com.example.datawash;

import com.example.datawash.utils.ReadExcelUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class DataWashApplicationTests {

    @Test
    void contextLoads() {
        try {
            ReadExcelUtil readExcelUtil = new ReadExcelUtil("C:\\Users\\颐信科技\\Desktop\\数据清洗\\整理.xlsx");
            String[] excelTitle = readExcelUtil.readExcelTitle();

            System.out.println(excelTitle.length);

            Map map = readExcelUtil.readExcelContent(excelTitle);

            System.out.println(map);

        } catch (Exception e) {

        }


    }

}
