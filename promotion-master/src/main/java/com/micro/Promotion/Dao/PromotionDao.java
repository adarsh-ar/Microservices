package com.micro.Promotion.Dao;

import com.micro.Promotion.model.PromotionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PromotionDao extends JpaRepository<PromotionModel, String> {
}
