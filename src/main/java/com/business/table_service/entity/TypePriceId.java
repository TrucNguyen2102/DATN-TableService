package com.business.table_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class TypePriceId implements Serializable {
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "price_id")
    private Integer priceId;

    public TypePriceId() {

    }

    public TypePriceId(Integer typeId, Integer priceId) {
        this.typeId = typeId;
        this.priceId = priceId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    
}
