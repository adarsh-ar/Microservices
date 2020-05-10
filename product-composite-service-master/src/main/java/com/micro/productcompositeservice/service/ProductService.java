package com.micro.productcompositeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.productcompositeservice.adaptor.InventoryAdaptor;
import com.micro.productcompositeservice.adaptor.PriceAdaptor;
import com.micro.productcompositeservice.adaptor.ProductAdaptor;
import com.micro.productcompositeservice.adaptor.PromotionAdaptor;
import com.micro.productcompositeservice.model.InventoryModel;
import com.micro.productcompositeservice.model.PriceModel;
import com.micro.productcompositeservice.model.ProductCreationModel;
import com.micro.productcompositeservice.model.ProductModel;
import com.micro.productcompositeservice.model.PromotionModel;
import com.micro.productcompositeservice.util.ProductUtil;

import rx.Observable;
import rx.schedulers.Schedulers;

@Service
public class ProductService {
	
	@Autowired
	private InventoryAdaptor inventoryAdaptor;
	
	@Autowired
	private PriceAdaptor priceAdaptor;
	
	@Autowired
	private ProductAdaptor productAdaptor;
	
	@Autowired
	private PromotionAdaptor promotionAdaptor;
	
	@Autowired
	private ProductUtil productUtil;
	
	public void  createProduct(ProductCreationModel productModel) {
		
		

		 Observable.fromCallable(() -> {
			  inventoryAdaptor.createInventoryRecord(productUtil.getInventoryModel(productModel));
		      return null;
		    }).subscribeOn(Schedulers.newThread()).subscribe();
		
		Observable.fromCallable(() -> {
			priceAdaptor.createPriceRecord(productUtil.getPriceModel(productModel));
		      return null;
		    }).subscribeOn(Schedulers.newThread()).subscribe();
		
		 Observable.fromCallable(() -> {
			productAdaptor.createProductRecord(productUtil.getProductModel(productModel));
		      return null;
		    }).subscribeOn(Schedulers.newThread()).subscribe();
		
		 Observable.fromCallable(() -> {
			promotionAdaptor.createPromotionRecord(productUtil.getPromotionModel(productModel));
		      return null;
		    }).subscribeOn(Schedulers.newThread()).subscribe();
		 
	}

	public Observable<ProductCreationModel> getProductDetails(Long productId) {
		
		Observable<InventoryModel> invObservable = Observable.fromCallable(() -> {
			 return inventoryAdaptor.getInventoryRecord(productId);
		      
		    }).subscribeOn(Schedulers.newThread());
		
		Observable<PriceModel> priceObservable = Observable.fromCallable(() -> {
			 return priceAdaptor.getPriceRecord(productId);
		      
		    }).subscribeOn(Schedulers.newThread());
		
		Observable<ProductModel> productObservable = Observable.fromCallable(() -> {
			 return productAdaptor.getProductRecord(productId);
		      
		    }).subscribeOn(Schedulers.newThread());
		
		Observable<PromotionModel> promotionObservable = Observable.fromCallable(() -> {
			 return promotionAdaptor.getPromotionRecord(productId);
		      
		    }).subscribeOn(Schedulers.newThread());
		
		
		return Observable.zip(invObservable, priceObservable, productObservable, promotionObservable, (a, b, c, d) -> productUtil.formProductResult(a,b,c,d));
	}

	
	
}
