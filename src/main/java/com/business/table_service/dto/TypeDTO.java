package com.business.table_service.dto;

import com.business.table_service.entity.Type;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class TypeDTO {
    private Integer id;
    private String name;
//    private Set<Integer> priceIds;

    private List<Integer> priceIds;




    public TypeDTO () {

    }

    public TypeDTO(Integer id, String name, List<Integer> priceIds) {
        this.id = id;
        this.name = name;
        this.priceIds = priceIds;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPriceIds() {
        return priceIds;
    }

    public void setPriceIds(List<Integer> priceIds) {
        this.priceIds = priceIds;
    }



}
