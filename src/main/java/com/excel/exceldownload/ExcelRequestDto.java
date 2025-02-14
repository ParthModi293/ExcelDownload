package com.excel.exceldownload;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExcelRequestDto {

    private List<Map<String,String>> dataList;
    private Map<String,String> columnHeader;

  /*  public List<Map<String, String>> getDataList() {
        return dataList;
    }

    public void setDataList(List<Map<String, String>> dataList) {
        this.dataList = dataList;
    }

    public Map<String, String> getColumnHeader() {
        return columnHeader;
    }

    public void setColumnHeader(Map<String, String> columnHeader) {
        this.columnHeader = columnHeader;
    }*/
}
