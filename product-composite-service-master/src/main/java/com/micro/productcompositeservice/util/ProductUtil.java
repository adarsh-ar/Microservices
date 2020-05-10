package com.micro.productcompositeservice.util;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Repository;

import com.micro.productcompositeservice.model.InventoryModel;
import com.micro.productcompositeservice.model.PriceModel;
import com.micro.productcompositeservice.model.ProductCreationModel;
import com.micro.productcompositeservice.model.ProductModel;
import com.micro.productcompositeservice.model.PromotionModel;

@Repository
public class ProductUtil {
	
	public static final String HEADER_ACCEPT = "Accept";

	public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";
	
	public  HttpHeaders setHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(APPLICATION_JSON);
		headers.set(HEADER_ACCEPT, HEADER_CONTENT_TYPE_VALUE);
		return headers;
	}

	public InventoryModel getInventoryModel(ProductCreationModel productModel) {
		
		InventoryModel invModel = new InventoryModel();
		
		invModel.setProductId(productModel.getProductId());
		invModel.setAvailableQuantity(productModel.getAvailableQuantity());
		invModel.setManufacturer(productModel.getManufacturer());
		invModel.setMaxQuantity(productModel.getMaxQuantity());
		invModel.setMinQuantity(productModel.getMinQuantity());
		invModel.setReturnQuantity(productModel.getReturnQuantity());
		invModel.setTotalQuantity(productModel.getTotalQuantity());
		
		return invModel;
	}

	public PriceModel getPriceModel(ProductCreationModel productModel) {
		
		PriceModel priceModel = new PriceModel();
		
		priceModel.setProductId(productModel.getProductId());
		priceModel.setPrice(productModel.getPrice());
		priceModel.setBasePrice(productModel.getBasePrice());
		priceModel.setSellingPrice(productModel.getSellingPrice());
		
		return priceModel;
	}

	public ProductModel getProductModel(ProductCreationModel productModel) {
		ProductModel model = new ProductModel();
		
		model.setDescription(productModel.getDescription());
		model.setDiscount(productModel.getDiscount());
		model.setIs_Sellable(productModel.isIsSellable());
		model.setProduct_Id(productModel.getProductId());
		model.setProduct_Name(productModel.getProductName());
		model.setReturnable(productModel.isReturnable());
		
		
		return model;
	}

	public PromotionModel getPromotionModel(ProductCreationModel productModel) {
		
		PromotionModel model = new PromotionModel();
		
		model.setDiscountType(productModel.getDiscountType());
		model.setEndDate(productModel.getEndDate());
		model.setProductId(productModel.getProductId());
		model.setPromotionName(productModel.getPromotionName());
		model.setStartDate(productModel.getStartDate());
		model.setStatus(productModel.getStatus());
		
		return model;
	}
	
	public ProductCreationModel formProductResult(InventoryModel invModel, PriceModel priceModel, ProductModel proModel, PromotionModel promoModel ) {
		ProductCreationModel model = new ProductCreationModel();
		
		model.setProductId(invModel.getProductId());
		model.setAvailableQuantity(invModel.getAvailableQuantity());
		model.setManufacturer(invModel.getManufacturer());
		model.setMaxQuantity(invModel.getMaxQuantity());
		model.setMinQuantity(invModel.getMinQuantity());
		model.setReturnQuantity(invModel.getReturnQuantity());
		model.setTotalQuantity(invModel.getTotalQuantity());
		
		model.setPrice(priceModel.getPrice());
		model.setBasePrice(priceModel.getBasePrice());
		model.setSellingPrice(priceModel.getSellingPrice());
		
		model.setDescription(proModel.getDescription());
		model.setDiscount(proModel.getDiscount());
		model.setIsSellable(proModel.isIs_Sellable());
		model.setProduct_Name(proModel.getProduct_Name());
		model.setReturnable(proModel.isReturnable());
		
		model.setDiscountType(promoModel.getDiscountType());
		model.setEndDate(promoModel.getEndDate());
		model.setProductId(promoModel.getProductId());
		model.setPromotionName(promoModel.getPromotionName());
		model.setStartDate(promoModel.getStartDate());
		model.setStatus(promoModel.getStatus());
		
		return model;
	}
	

}
