package com.business.table_service.service;

import com.business.table_service.entity.Price;
import com.business.table_service.exception.ResourceNotFoundException;
import com.business.table_service.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class PriceServiceImpl implements PriceService{
    @Autowired
    private PriceRepo priceRepo;

    @Override
    public Price addPrice(Price price) {
        return priceRepo.save(price);
    }

    @Override
    public List<Price> getAllPrices() {
        return priceRepo.findAll();
    }




    @Override
    public Price updatePrice(Integer id, Price price) {
        Optional<Price> existingPrice = priceRepo.findById(id);
        if (existingPrice.isPresent()) {
            Price updatedPrice = existingPrice.get();
            updatedPrice.setPrice(price.getPrice());
            updatedPrice.setStartDate(price.getStartDate());
            updatedPrice.setEndDate(price.getEndDate());
            return priceRepo.save(updatedPrice);
        } else {
            throw new ResourceNotFoundException("Giá không tồn tại với ID: " + id);
        }
    }

    @Override
    public void deletePrice(Integer id) {
        if (!priceRepo.existsById(id)) {
            throw new ResourceNotFoundException("Giá không tồn tại với ID: " + id);
        }
        priceRepo.deleteById(id);
    }

    public Price getTablePriceById(Integer priceId) {
        // Tìm giá của bàn theo priceId trong cơ sở dữ liệu
        return priceRepo.findById(priceId).orElse(null);  // Trả về null nếu không tìm thấy
    }

//    @Override
//    public Double getPriceById(List<Integer> priceIds) {
//        // Giả định bạn chỉ lấy giá của một ID, nếu priceIds có nhiều hơn một ID, hãy điều chỉnh lại.
//        if (priceIds.isEmpty()) return null;
//
//        Price price = priceRepo.findById(priceIds.get(0)).orElse(null);
//        return price != null ? price.getPrice() : null;
//    }





}
