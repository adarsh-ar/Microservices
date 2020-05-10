package com.micro.Promotion.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "promotion")
@Data
public class PromotionModel {
    @Id
    @Column(name="PRODUCTID")
    String ProductId;
    @Column(name="PROMOTIONNAME")
    String PromotionName;
    @Column(name="STARTDATE")
    Date StartDate;
    @Column(name ="ENDDATE")
    Date EndDate;
    @Column(name = "DISCOUNTTYPE")
    String DiscountType;
    @Column(name = "STATUS")
    String Status;
}
