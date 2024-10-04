package com.business.table_service.service;

import com.business.table_service.repository.TypePriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypePriceServiceImpl implements TypeService{
    @Autowired
    private TypePriceRepo typePriceRepo;
}
