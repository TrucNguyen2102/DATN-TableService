package com.business.table_service.dto;

public class TablePlayInfo {
    private Integer tableNum;
    private String typeName;

    public TablePlayInfo(Integer tableNum, String typeName) {
        this.tableNum = tableNum;
        this.typeName = typeName;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
