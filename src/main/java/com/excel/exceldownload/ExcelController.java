package com.excel.exceldownload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {

    @Autowired
    private ExcelService excelService;

    @Autowired
    private CsvService csvService;

    @PostMapping("/excel")
    public ResponseEntity<?> downloadExcel(@RequestBody ExcelRequestDto excelRequestDto) {

           excelService.generateExcel(excelRequestDto);
        return ResponseEntity.ok()
               .body("Download");


    }

    @PostMapping("/csv")
    public ResponseEntity<?> downloadCsv(@RequestBody ExcelRequestDto excelRequestDto) {

        csvService.generateCsv(excelRequestDto);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body("Download");


    }
}
