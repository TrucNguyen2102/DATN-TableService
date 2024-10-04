package com.business.table_service.repository;

import com.business.table_service.entity.TablePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TablePlayRepo extends JpaRepository<TablePlay, Integer> {
}
