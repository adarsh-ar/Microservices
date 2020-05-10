package com.micro.productcompositeservice.model;

import java.util.Date;

public class PromotionModel {
	
	private Long ProductId;
	private String PromotionName;
    private Date StartDate;
    private Date EndDate;
    private String DiscountType;
    private String Status;
    
	public Long getProductId() {
		return ProductId;
	}
	public void setProductId(Long productId) {
		ProductId = productId;
	}
	public String getPromotionName() {
		return PromotionName;
	}
	public void setPromotionName(String promotionName) {
		PromotionName = promotionName;
	}
	public Date getStartDate() {
		return StartDate;
	}
	public void setStartDate(Date startDate) {
		StartDate = startDate;
	}
	public Date getEndDate() {
		return EndDate;
	}
	public void setEndDate(Date endDate) {
		EndDate = endDate;
	}
	public String getDiscountType() {
		return DiscountType;
	}
	public void setDiscountType(String discountType) {
		DiscountType = discountType;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
    
    
    

    
}
