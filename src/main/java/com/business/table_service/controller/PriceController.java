package com.business.table_service.controller;

import com.business.table_service.entity.Price;
import com.business.table_service.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tables")
public class PriceController {
    @Autowired
    private PriceService priceService;

    // API để thêm giá mới

    @PostMapping("/prices/add")
    public ResponseEntity<Price> addPrice(@RequestBody Price price) {
        try {
            // Trường hợp không có ngày kết thúc -> gán mặc định là null
            if (price.getEndDate() == null) {
                price.setEndDate(null);  // Gán null nếu không có ngày kết thúc
            }
            // Trường hợp có ngày kết thúc -> giữ nguyên giá trị do client cung cấp
            Price newPrice = priceService.addPrice(price);
            return ResponseEntity.ok(newPrice);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }
    }


//    @PostMapping("/prices/add")
//    public ResponseEntity<Price> addPrice(@RequestBody Price price) {
//        try {
//            Price newPrice = priceService.addPrice(price);
//            return ResponseEntity.ok(newPrice);
//        }catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(null);
//        }
//
//    }
//    @GetMapping("/prices/all")
//    public ResponseEntity<List<Price>> getAllPrices() {
//        try {
//            List<Price> prices = priceService.getAllPrices();
//            System.out.println("Dữ liệu từ service: " + prices);
//            return ResponseEntity.ok(prices);
//        }catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.badRequest().body(null);
//        }
//
//    }

    // API lấy tất cả giá
    @GetMapping("/prices/all")
    public ResponseEntity<List<Price>> getAllPrices() {
        try {
            List<Price> prices = priceService.getAllPrices();
            if (prices.isEmpty()) {
                return ResponseEntity.noContent().build(); // Nếu danh sách rỗng, trả về mã 204 (No Content)
            }
            return ResponseEntity.ok(prices); // Trả về danh sách giá kèm mã 200 (OK)
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // Trả về lỗi 500 (Internal Server Error)
        }
    }

    //API cập nhật giá
    @PutMapping("/prices/update/{id}")
    public ResponseEntity<Price> updatePrice(@PathVariable Integer id, @RequestBody Price price) {
        Price updatedPrice = priceService.updatePrice(id, price);
        return ResponseEntity.ok(updatedPrice);
    }

    //API xóa giá
    @DeleteMapping("/prices/delete/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable Integer id) {
        priceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }


}