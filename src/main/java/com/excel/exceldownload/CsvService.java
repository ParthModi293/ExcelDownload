package com.excel.exceldownload;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class CsvService {

    public String generateCsv(ExcelRequestDto excelRequestDto) {

        try {
            File f = new File("/home/bizott-2/CodingPractice/example.csv");
            FileWriter fileWriter=new FileWriter(f);
             CSVWriter csvWriter = new CSVWriter(fileWriter);



            Map<String, String> columnHeaders = excelRequestDto.getColumnHeader();
            List<String> headerNames = List.copyOf(columnHeaders.values());

            csvWriter.writeNext(headerNames.toArray(new String[0]));

            /*writer.write(String.join(",", headerNames));
            writer.write("\n");*/

            List<Map<String, String>> dataList = excelRequestDto.getDataList();
            for (Map<String, String> data : dataList) {

//                StringBuilder row = new StringBuilder();

                String[] rowData = new String[headerNames.size()];
                int index = 0;

                for (String headerKey : columnHeaders.keySet()) {
                    rowData[index++] =  data.getOrDefault(headerKey, "");
                }

                csvWriter.writeNext(rowData);
            }
            csvWriter.flush();
           return  f.getAbsolutePath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
