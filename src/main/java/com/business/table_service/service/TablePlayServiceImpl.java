package com.business.table_service.service;

import com.business.table_service.repository.TablePlayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TablePlayServiceImpl implements TablePlayService{
    @Autowired
    private TablePlayRepo tablePlayRepo;
}
