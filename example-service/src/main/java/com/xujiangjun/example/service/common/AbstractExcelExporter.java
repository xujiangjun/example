package com.xujiangjun.example.service.common;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 通用导出Excel
 * 如果是大数据Excel，可以考虑使用hutool或者easyexcel
 *
 * @author jiangjun.xu
 * @date 2020-03-13 11:26
 */
@Slf4j
public abstract class AbstractExcelExporter {

    /**
     * 导出Excel
     *
     * @param filename filename需要以文件扩展名结尾，如xls
     * @param workbook 已制作好需要导出的Excel
     * @param response HttpServletResponse
     */
    public void exportExcel(String filename, Workbook workbook, HttpServletResponse response) {
        try {
            setResponseHeader(filename, response);
            @Cleanup OutputStream os = response.getOutputStream();
            workbook.write(os);
            os.flush();
        } catch (Exception e) {
            log.error("Excel导出失败", e);
            throw new RuntimeException("Excel导出失败");
        }
    }

    /**
     * 制作Sheet并填充内容
     *
     * @param workbook    工作簿
     * @param sheetName   工作表
     * @param headers     表头
     * @param dataMapList 数据内容
     * @return 制作好的工作簿
     */
    public Workbook makeSheet(Workbook workbook, String sheetName, String[] headers, List<Map<String, Object>> dataMapList) {
        if (workbook == null) {
            workbook = new HSSFWorkbook();
        }
        Sheet sheet = workbook.createSheet(sheetName);
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        Row firstRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = firstRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
        for (int i = 0; i < dataMapList.size(); i++) {
            Map<String, Object> dataMap = dataMapList.get(i);
            Row row = sheet.createRow(i + 1);
            fittingContent(row, dataMap);
        }
        return workbook;
    }

    /**
     * 子类需重写此方法，和下面类似赋值即可
     * int columnNum = 0;
     * row.createCell(columnNum++).setCellValue("");
     *
     * @param row     当前行
     * @param dataMap 当前行填充的数据
     */
    protected abstract void fittingContent(Row row, Map<String, Object> dataMap);

    private void setResponseHeader(String filename, HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
            response.addHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            log.error("Excel导出失败", e);
            throw new RuntimeException("Excel导出失败");
        }
    }
}
