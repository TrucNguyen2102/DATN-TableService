package com.business.table_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "type_price")
public class Type_Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
}
