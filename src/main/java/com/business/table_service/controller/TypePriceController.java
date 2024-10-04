package com.business.table_service.controller;

import com.business.table_service.service.TypePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/type_price")
public class TypePriceController {
    @Autowired
    private TypePriceService typePriceService;
}
