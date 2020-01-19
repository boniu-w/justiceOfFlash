package com.example.zijinchuantoupingtai.util;

import com.example.zijinchuantoupingtai.entity.Employee;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Iterator;
import java.util.List;

/**
 * @author wg
 * @Package com.example.zijinchuantoupingtai.util
 * @date 2019/11/21 9:24
 * @Copyright
 */
public class WriteToExcel {

    /**
     * 将List集合数据写入excel（单个sheet）
     *
     * @param filePath     文件路径
     * @param excelTitle   文件表头
     * @param employeeList 要写入的数据集合
     * @param sheetName    sheet名称
     */
    public static void writeEmployeeListToExcel(String filePath, String[] excelTitle, List<Employee> employeeList, String sheetName) {
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
            for (Iterator<Employee> employeeIter = employeeList.iterator(); employeeIter.hasNext(); ) {
                Employee employee = employeeIter.next();
                //create sheet row
                Row row = sheet.createRow(rowIndex);
                //create sheet coluum(单元格)
                Cell cell0 = row.createCell(0);
                cell0.setCellValue(employee.getName());
                Cell cell1 = row.createCell(1);
                cell1.setCellValue(employee.getGender());
                Cell cell2 = row.createCell(2);
                cell2.setCellValue(employee.getAge());
                Cell cell3 = row.createCell(3);
                cell3.setCellValue(employee.getDepartment());
                Cell cell4 = row.createCell(4);
                cell4.setCellValue(employee.getSalary());
                Cell cell5 = row.createCell(5);
                cell5.setCellValue(employee.getDate());
                rowIndex++;
            }
//            System.out.println("主表数据写入完成>>>>>>>>");
            FileOutputStream fos = new FileOutputStream(filePath);
            workbook.write(fos);
            fos.close();
//            System.out.println(filePath + "写入文件成功>>>>>>>>>>>");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没用到
     * 读取Excel2003的表头
     *
     * @param filePath 需要读取的文件路径
     * @return
     */
    public static String[] readHeaderFromXLS2003(String filePath) {
        String[] excelTitle = null;
        FileInputStream is = null;
        try {
            File excelFile = new File(filePath);
            is = new FileInputStream(excelFile);
            HSSFWorkbook workbook2003 = new HSSFWorkbook(is);
            //循环读取工作表
            for (int i = 0; i < workbook2003.getNumberOfSheets(); i++) {
                HSSFSheet hssfSheet = workbook2003.getSheetAt(i);
                //*************获取表头是start*************
                HSSFRow sheetRow = hssfSheet.getRow(i);
                excelTitle = new String[sheetRow.getLastCellNum()];
                for (int k = 0; k < sheetRow.getLastCellNum(); k++) {
                    HSSFCell hssfCell = sheetRow.getCell(k);
                    excelTitle[k] = hssfCell.getStringCellValue();
//		            	System.out.println(excelTitle[k] + " ");
                }
                //*************获取表头end*************
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {// 关闭文件流
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return excelTitle;
    }
}
