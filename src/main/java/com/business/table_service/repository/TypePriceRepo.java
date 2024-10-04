package com.business.table_service.repository;

import com.business.table_service.entity.TypePrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePriceRepo extends JpaRepository<TypePrice, Integer> {
}
