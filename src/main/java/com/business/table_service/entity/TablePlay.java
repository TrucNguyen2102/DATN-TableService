package com.business.table_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "table_play")
public class TablePlay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "table_num", nullable = false)
    private Integer tableNum;

    @Column(name = "table_status", length = 50, nullable = false)
    private String tableStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false, referencedColumnName = "id")
    private Type type;

    public TablePlay() {

    }

    public TablePlay(Integer id, Integer tableNum, String tableStatus, Type type) {
        this.id = id;
        this.tableNum = tableNum;
        this.tableStatus = tableStatus;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
