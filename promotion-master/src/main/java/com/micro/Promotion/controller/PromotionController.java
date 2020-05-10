package com.micro.Promotion.controller;

import com.micro.Promotion.model.PromotionModel;
import com.micro.Promotion.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController()
public class PromotionController {
    @Autowired
    PromotionService service;
    @GetMapping(path="/getpromo/{id}")
    public PromotionModel getPromotion(@PathVariable String id) {
        return service.getPromo(id);
    }
    
    @PostMapping(path="/create")
    public String createPromo(@RequestBody PromotionModel request){
        return service.createPromotion(request);
    }
    
    @PatchMapping(path="/update")
    public String updatePromo(@RequestBody PromotionModel request){
        return service.createPromotion(request);
    }
    
    @DeleteMapping(path="/delete/{id}")
    public PromotionModel deletePromo(@PathVariable String id){
        return service.delete(id);
    }
}
