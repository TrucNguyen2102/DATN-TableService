package com.business.table_service.dto;

public class TablePlayDTO {

    private Integer tableNum;
    private String tableStatus;
    private Integer typeId;

    public TablePlayDTO() {

    }

    public TablePlayDTO(Integer tableNum, String tableStatus, Integer typeId) {

        this.tableNum = tableNum;
        this.tableStatus = tableStatus;
        this.typeId = typeId;
    }



    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
}
