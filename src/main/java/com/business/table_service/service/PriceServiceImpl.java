package com.business.table_service.service;

import com.business.table_service.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements PriceService{
    @Autowired
    private PriceRepo priceRepo;
}
