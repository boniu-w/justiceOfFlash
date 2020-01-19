package com.example.zijinchuantoupingtai.util;

import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.util
 * @date 2019/11/20 9:00
 * @Copyright
 */
public class ReadExcelUtil {


    private Logger logger = LoggerFactory.getLogger(ReadExcelUtil.class);
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public ReadExcelUtil() {

    }

    public ReadExcelUtil(String filepath) {
        if (filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);

            switch (ext) {
                case ".xls":
                    wb = new HSSFWorkbook(is);
                    break;
                case ".xlsx":
                    wb = new XSSFWorkbook(is);
                    break;
                case ".et":
                    wb = new XSSFWorkbook(is);
                    break;
                default:
                    wb = null;
                    break;
            }

//            if (".xls".equals(ext)) {
//                wb = new HSSFWorkbook(is);
//            } else if (".xlsx".equals(ext)) {
//                wb = new XSSFWorkbook(is);
//            } else {
//                wb = null;
//            }
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @param
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public String[] readExcelTitle() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
//        System.out.println("colNum:" + colNum);

        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
//            title[i] = getStringCellValue(row.getCell((short) i));
//            title[i] = row.getCell(i).getCellFormula();
            title[i] = row.getCell(i).getStringCellValue();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @return Map 包含单元格数据内容的Map对象
     */
    public Map<Integer, Map<Integer, Object>> readExcelContentTOMap() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }

        Map<Integer, Map<Integer, Object>> content = new HashMap<Integer, Map<Integer, Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        // 总列数
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<Integer, Object> cellValue = new HashMap<Integer, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put(j, obj);
                j++;
            }
            content.put(i, cellValue);

        }
        return content;
    }

    /**
     * 读取Excel数据内容 返回set 保证 空行不输出, 去掉重复的行
     */
    public Set<List> readExcelContentToSet() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        HashSet<List> hashSet = new HashSet<>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();


        // 总列数
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();

        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);

            int j = 0;
            ArrayList<Object> arrayList = new ArrayList<>();
            while (j < colNum) {

                String obj = (String) getCellFormatValue(row.getCell(j));


                arrayList.add(obj);
                j++;
            }


            hashSet.add(arrayList);
        }
        return hashSet;
    }

    /**
     * 根据Cell类型设置数据
     *
     * @param cell
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字

                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }


}
