package com.business.table_service.service;

import com.business.table_service.entity.Price;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface PriceService {
    Price addPrice(Price price);
    List<Price> getAllPrices();



    Price updatePrice(Integer id, Price price);

    boolean lockPrice(Integer id);

    boolean isPriceInUse(Integer priceId);

    void deletePrice(Integer id);

    Price getTablePriceById(Integer priceId);

    Page<Price> getAllPrices(Pageable pageable);
}
