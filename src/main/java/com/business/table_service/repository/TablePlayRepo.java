package com.business.table_service.repository;

import com.business.table_service.entity.TablePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TablePlayRepo extends JpaRepository<TablePlay, Integer> {
    boolean existsByTableNum(Integer tableNum);

    @Query("SELECT t FROM TablePlay t JOIN t.type tp JOIN tp.typePrices price")
    List<TablePlay> findAllTablesWithPrices();


}
