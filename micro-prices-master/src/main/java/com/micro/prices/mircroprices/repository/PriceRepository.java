package com.micro.prices.mircroprices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.prices.mircroprices.model.Price;

@Repository
public interface PriceRepository extends  JpaRepository<Price, Long> {

}
