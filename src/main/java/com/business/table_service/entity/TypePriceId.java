package com.business.table_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TypePriceId implements Serializable {
//    @Column(name = "type_id")
    private Integer typeId;

//    @Column(name = "price_id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypePriceId that = (TypePriceId) o;
        return Objects.equals(typeId, that.typeId) && Objects.equals(priceId, that.priceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, priceId);
    }
}
