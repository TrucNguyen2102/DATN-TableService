package com.business.table_service.service;

import com.business.table_service.dto.TypeDTO;
import com.business.table_service.entity.Type;

import java.util.List;

public interface TypeService {
//    Type addType(Type type);

    Type addType(TypeDTO typeDTO);
    List<Type> getAllTypes();

//    Type updateType(Integer id, Type type);
    Type updateType(Integer id, TypeDTO typeDTO);

    void deleteType(Integer id);



    TypeDTO getTypeById(Integer typeId);
}
