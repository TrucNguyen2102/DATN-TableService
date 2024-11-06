package com.business.table_service.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "type_price")
public class TypePrice implements Serializable {
    @EmbeddedId
    private TypePriceId id;

    @ManyToOne
    @MapsId("typeId")
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @MapsId("priceId")
    @JoinColumn(name = "price_id")
    private Price price;

    public TypePrice() {
        this.id = new TypePriceId();
    }

    public TypePrice(Type type, Price price) {
        this.type = type;
        this.price = price;
        this.id = new TypePriceId(type.getId(), price.getId());
    }

    public TypePriceId getId() {
        return id;
    }

    public void setId(TypePriceId id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
//        this.id.setTypeId(type.getId());
        if (this.id != null) {
            this.id.setTypeId(type.getId());
        }
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
//        this.id.setPriceId(price.getId());
        if (this.id != null) {
            this.id.setPriceId(price.getId());
        }
    }
}
