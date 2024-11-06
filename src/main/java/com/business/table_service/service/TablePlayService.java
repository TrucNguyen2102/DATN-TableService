package com.business.table_service.service;

import com.business.table_service.dto.TablePlayWithPriceDTO;
import com.business.table_service.entity.TablePlay;

import java.util.List;
import java.util.Optional;

public interface TablePlayService {
    List<TablePlay> getAllTables();
//    List<TablePlayResponse> getAllTables();



    TablePlay addTablePlay(TablePlay tablePlay);

    TablePlay updateTable(Integer id, TablePlay newTablePlay);


    void deleteTable(Integer id);

    List<TablePlayWithPriceDTO> getAllTablesWithPrices();


    Optional<TablePlay> findById(Integer id);

    boolean updateTableStatus(Integer tableId, String status);

    TablePlayWithPriceDTO getTableWithPriceById(Integer id);


}
