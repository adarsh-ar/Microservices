package com.micro.productcompositeservice.model;

public class ProductModel {

	Long product_Id;

	String product_Name;

	String description;
	
	boolean is_Sellable;
	
	boolean Returnable;

	String Discount;

	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(Long product_Id, String product_Name, String description, boolean is_Sellable, boolean returnable,
			String discount) {
		super();
		this.product_Id = product_Id;
		this.product_Name = product_Name;
		this.description = description;
		this.is_Sellable = is_Sellable;
		Returnable = returnable;
		Discount = discount;
	}

	public Long getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(Long product_Id) {
		this.product_Id = product_Id;
	}

	public String getProduct_Name() {
		return product_Name;
	}

	public void setProduct_Name(String product_Name) {
		this.product_Name = product_Name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isIs_Sellable() {
		return is_Sellable;
	}

	public void setIs_Sellable(boolean is_Sellable) {
		this.is_Sellable = is_Sellable;
	}

	public boolean isReturnable() {
		return Returnable;
	}

	public void setReturnable(boolean returnable) {
		Returnable = returnable;
	}

	public String getDiscount() {
		return Discount;
	}

	public void setDiscount(String discount) {
		Discount = discount;
	}

}
