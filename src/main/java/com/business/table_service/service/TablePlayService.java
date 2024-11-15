package com.business.table_service.service;

import com.business.table_service.dto.TablePlayInfo;
import com.business.table_service.dto.TablePlayWithPriceDTO;
import com.business.table_service.dto.TableStatusUpdateRequest;
import com.business.table_service.entity.TablePlay;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TablePlayService {
    Page<TablePlay> getAllTables(Pageable pageable);
    List<TablePlay> getAllTables();

    TablePlay addTablePlay(TablePlay tablePlay);

    TablePlay updateTable(Integer id, TablePlay newTablePlay);


    void deleteTable(Integer id);

    List<TablePlayWithPriceDTO> getAllTablesWithPrices();


    Optional<TablePlay> findById(Integer id);

    boolean updateTableStatus(Integer tableId, String status);

    TablePlayWithPriceDTO getTableWithPriceById(Integer id);


    List<TablePlay> getTablesByStatus(String status);

}
