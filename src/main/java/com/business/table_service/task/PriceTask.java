package com.business.table_service.task;

import com.business.table_service.entity.Price;
import com.business.table_service.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class PriceTask {

    @Autowired
    private PriceRepo priceRepo;

    public PriceTask() {


    }

    public PriceTask(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Scheduled(cron = "*/1 * * * * *") // Chạy mỗi giây
    public void priceCleanUpTask () {
        List<Price> priceList = priceRepo.findAll();
        LocalDate today = LocalDate.now(); // Ngày hiện tại
        for(Price price : priceList) {
            // Kiểm tra nếu `endDate` đã qua
//            if (today.isAfter(price.getEndDate())) {
            if (price.getEndDate() != null && today.isAfter(price.getEndDate())) {
                price.setActive(false); // Hết hạn
            } else {
                price.setActive(true); // Còn áp dụng
            }
        }
        priceRepo.saveAll(priceList);
    }
}
