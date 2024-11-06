package com.business.table_service.repository;

import com.business.table_service.entity.TypePrice;
import com.business.table_service.entity.TypePriceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePriceRepo extends JpaRepository<TypePrice, TypePriceId> {
    TypePrice findByTypeId(Integer id);
}
