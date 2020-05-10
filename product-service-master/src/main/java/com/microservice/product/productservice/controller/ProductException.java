package com.microservice.product.productservice.controller;

public class ProductException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 633315492951624936L;

	public ProductException(String exception) {
		super(exception);
	}

	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

}
