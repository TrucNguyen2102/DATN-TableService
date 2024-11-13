package com.business.table_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "type")
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Chỉ định phía bị quản lý
    private Set<TypePrice> typePrices = new HashSet<>();

    public List<Integer> getPriceIds() {
        Set<Integer> priceIds = new HashSet<>();
        for (TypePrice typePrice : typePrices) {
            priceIds.add(typePrice.getPrice().getId()); // Lấy priceId từ TypePrice
        }
//        return priceIds;
        return new ArrayList<>(priceIds);
    }

    public Type() {
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

    public Set<TypePrice> getTypePrices() {
        return typePrices;
    }

    public void setTypePrices(Set<TypePrice> typePrices) {
        this.typePrices = typePrices;
    }
}
