package com.micro.Promotion.service;

import com.micro.Promotion.Dao.PromotionDao;
import com.micro.Promotion.model.PromotionModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PromotionService {
    @Autowired
    PromotionDao service;

    @HystrixCommand(fallbackMethod = "fallBackPromo")
    public PromotionModel getPromo(String id) {
        PromotionModel promo = service.findById(id).get();

        return promo;
    }

    private PromotionModel fallBackPromo(String id) {
        return null;
    }


    @HystrixCommand(fallbackMethod = "fallBackCreate")
    public String createPromotion(PromotionModel request) {
        try {
            service.save(request);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed";
        }
    }

    private String fallBackCreate(PromotionModel request) {
        return null;
    }


    @HystrixCommand(fallbackMethod = "fallBackDelete")
    public PromotionModel delete(String id) {
        PromotionModel promo = service.findById(id).get();
       
        return promo;

    }

    private PromotionModel fallBAckDelete(BigDecimal id) {
        return null;
    }
}
