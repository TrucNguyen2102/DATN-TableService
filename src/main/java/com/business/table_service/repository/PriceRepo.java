package com.business.table_service.repository;

import com.business.table_service.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepo extends JpaRepository<Price, Integer> {

    Price getPriceById(Integer priceId);
}
