package com.business.table_service.controller;

import com.business.table_service.dto.TypeDTO;
import com.business.table_service.entity.TypePrice;
import com.business.table_service.entity.TypePriceId;
import com.business.table_service.service.TypePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
public class TypePriceController {
    @Autowired
    private TypePriceService typePriceService;

//    @GetMapping("/type-prices/all")
//    public ResponseEntity<List<TypeDTO>> getAllTypePrices() {
//        List<TypeDTO> typePrices = typePriceService.getAllTypePrices();
//        return ResponseEntity.ok(typePrices);
//    }

//    @GetMapping("/all")
//    public List<TypePrice> getAllTypePrices() {
//        return typePriceService.getAllTypePrices();
//    }

//    @PostMapping("/add")
//    public TypePrice addTypePrice(@RequestBody TypePrice typePrice) {
//        return typePriceService.addTypePrice(typePrice);
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteTypePrice(@RequestBody TypePriceId id) {
//        typePriceService.deleteTypePrice(id);
//        return ResponseEntity.noContent().build();
//    }
}
