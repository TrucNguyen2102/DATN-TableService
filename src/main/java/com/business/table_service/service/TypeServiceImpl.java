package com.business.table_service.service;

import com.business.table_service.repository.TypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    private TypeRepo typeRepo;
}
