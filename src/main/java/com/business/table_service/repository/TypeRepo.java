package com.business.table_service.repository;

import com.business.table_service.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepo extends JpaRepository<Type, Integer> {
    boolean existsByName(String name);
}
