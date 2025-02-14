package com.excel.exceldownload;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    public void generateExcel(ExcelRequestDto excelRequestDto) {


        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Sheet 1");
        sheet.setDefaultColumnWidth(15);
        Row headerRow = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
       XSSFFont font = (XSSFFont) workbook.createFont();
        font.setFontHeight(8);
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        Map<String, String> columnHeaders = excelRequestDto.getColumnHeader();
        List<String> headerNames = List.copyOf(columnHeaders.values());

        for (int i = 0; i < headerNames.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerNames.get(i));
            cell.setCellStyle(style);

        }

        List<Map<String, String>> dataList = excelRequestDto.getDataList();
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);

        for(int i=0; i < dataList.size(); i++){

            Row dataRow = sheet.createRow(i+2);
            Map<String, String> data = dataList.get(i);

            int columnIndex = 0;
           for(String headerKey : columnHeaders.keySet()){
               String value = data.get(headerKey);
               Cell cell = dataRow.createCell(columnIndex++);
               cell.setCellValue(value != null ? value : "");
              cell.setCellStyle(dataStyle);
           }


        }

        try (FileOutputStream out = new FileOutputStream("/home/bizott-2/CodingPractice/test.xlsx")) {
           workbook.write(out);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
