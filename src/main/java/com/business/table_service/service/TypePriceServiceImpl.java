package com.business.table_service.service;

import com.business.table_service.dto.TypeDTO;
import com.business.table_service.entity.TypePrice;
import com.business.table_service.entity.TypePriceId;
import com.business.table_service.repository.TypePriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypePriceServiceImpl implements TypePriceService{
    @Autowired
    private TypePriceRepo typePriceRepo;

//    public List<TypeDTO> getAllTypePrices() {
//        List<TypePrice> typePrices = typePriceRepo.findAll();
//        List<TypeDTO> typeDTOs = new ArrayList<>();
//
//        for (TypePrice typePrice : typePrices) {
//            TypeDTO dto = new TypeDTO(typePrice.getType().getId(), typePrice.getType().getName());
//            typeDTOs.add(dto);
//        }
//
//        return typeDTOs;
//    }

//    public List<TypePrice> getAllTypePrices() {
//        return typePriceRepo.findAll();
//    }

//    public TypePrice addTypePrice(TypePrice typePrice) {
//        return typePriceRepo.save(typePrice);
//    }
//
//    public void deleteTypePrice(TypePriceId id) {
//        typePriceRepo.deleteById(id);
//    }
}
