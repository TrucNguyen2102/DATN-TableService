package com.business.table_service.controller;

import com.business.table_service.service.TablePlayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tables")
public class TablePlayController {
    @Autowired
    private TablePlayService tablePlayService;
}
