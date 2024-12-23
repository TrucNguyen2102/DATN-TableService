package com.business.table_service.repository;

import com.business.table_service.entity.TablePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TablePlayRepo extends JpaRepository<TablePlay, Integer> {
    boolean existsByTableNum(Integer tableNum);

    @Query("SELECT t FROM TablePlay t JOIN t.type tp JOIN tp.typePrices price")
    List<TablePlay> findAllTablesWithPrices();


    List<TablePlay> findByTableStatus(String tableStatus);

    // Tìm bàn theo tableId
    Optional<TablePlay> findById(Integer tableId);

    // Tìm bàn theo loại
    List<TablePlay> findByTypeId(Integer typeId);
}
