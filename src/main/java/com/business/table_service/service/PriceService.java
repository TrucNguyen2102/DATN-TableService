package com.business.table_service.service;

import com.business.table_service.entity.Price;

import java.util.List;
import java.util.Set;

public interface PriceService {
    Price addPrice(Price price);
    List<Price> getAllPrices();



    Price updatePrice(Integer id, Price price);

    void deletePrice(Integer id);


}
