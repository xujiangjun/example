package com.xujiangjun.example.util;

import com.xujiangjun.example.common.util.DateUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.joda.time.DateTime;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author xujiangjun
 * @since 2018.12.24
 */
public class ExcelHelper {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        readExcel("/Users/xujiangjun/Downloads/inspect_excel.xlsx", "省分月度巡查",null, null);
    }

    public static void readExcel(String filePath, String sheetName, String[] properties, Class clazz)
            throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        Sheet sheet = workbook.getSheet(sheetName);
        if(sheet == null){
            return;
        }
        int firstRowNum = sheet.getFirstRowNum();
        int lastRowNum = sheet.getLastRowNum();
        Row firstRow = sheet.getRow(firstRowNum);
        int firstColumnNum = firstRow.getFirstCellNum();
        int lastColumnNum = firstRow.getLastCellNum();
        for (int i = firstRowNum; i <= lastRowNum; i++) {
            Row row = sheet.getRow(i);
            for (int j = firstColumnNum; j <= lastColumnNum; j++) {
                Cell cell = row.getCell(j);
                System.out.println(String.format("第%d行第%d列, value:%s", i + 1, j + 1, getCellValue(sheet, cell)));
            }
        }
    }

    public static String getCellValue(Sheet sheet, Cell cell){
        if(cell == null){
            return null;
        }
        List<CellRangeAddress> cellRangeAddressList = sheet.getMergedRegions();
        if(!isCombineCell(cellRangeAddressList, cell)){
            return getCellValue(cell);
        }
        for (CellRangeAddress cellRangeAddress : cellRangeAddressList) {
            int firstRow = cellRangeAddress.getFirstRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastRow = cellRangeAddress.getLastRow();
            int lastColumn = cellRangeAddress.getLastColumn();
            // 合并单元格只有左上角的单元格有值，其他均为null。此处给其他单元格也赋值
            if(cell.getRowIndex() >= firstRow && cell.getRowIndex() <= lastRow){
                if(cell.getColumnIndex() >= firstColumn && cell.getColumnIndex() <= lastColumn){
                    Row row = sheet.getRow(firstRow);
                    Cell usefulCell = row.getCell(firstColumn);
                    return getCellValue(usefulCell);
                }
            }
        }
        return null;
    }

    public static String getCellValue(Cell cell){
        String cellValue;
        switch (cell.getCellType()){
            case BLANK:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)){
                    cellValue = new DateTime(cell.getDateCellValue()).toString(DateUtils.fullFormatter);
                }else{
                    cellValue = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            default:
                cellValue = cell.getStringCellValue();
                break;
        }
        return cellValue;
    }

    public static boolean isCombineCell(List<CellRangeAddress> cellRangeAddressList, Cell cell){
        for (CellRangeAddress cellRangeAddress : cellRangeAddressList) {
            int firstRow = cellRangeAddress.getFirstRow();
            int firstColumn = cellRangeAddress.getFirstColumn();
            int lastRow = cellRangeAddress.getLastRow();
            int lastColumn = cellRangeAddress.getLastColumn();
            if(cell.getRowIndex() >= firstRow && cell.getRowIndex() <= lastRow){
                if(cell.getColumnIndex() >= firstColumn && cell.getColumnIndex() <= lastColumn){
                    return true;
                }
            }
        }
        return false;
    }

}
