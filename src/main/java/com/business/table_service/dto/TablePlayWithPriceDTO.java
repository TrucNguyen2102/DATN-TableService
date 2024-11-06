package com.business.table_service.dto;

public class TablePlayWithPriceDTO {
    private Integer id;
    private Integer tableNum;
    private String tableStatus;

    private String typeName;
    private double price;

    public TablePlayWithPriceDTO() {

    }

    public TablePlayWithPriceDTO(Integer id, Integer tableNum, String tableStatus, String typeName, double price) {
        this.id = id;
        this.tableNum = tableNum;
        this.tableStatus = tableStatus;
        this.typeName = typeName;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
