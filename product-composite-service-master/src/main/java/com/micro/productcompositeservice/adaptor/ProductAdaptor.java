package com.micro.productcompositeservice.adaptor;

import java.net.URI;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.micro.productcompositeservice.model.ProductModel;
import com.micro.productcompositeservice.util.ProductUtil;

@Repository
public class ProductAdaptor {
	
	@Autowired
	@Qualifier("productCreateRestemplate")
	private RestTemplate template;
	
	@Autowired
	private ProductUtil productUtil;
	
	@Value("${product.service.url}")
	private String url;
	
	@Value("${product.service.url.get}")
	private String getUrl;
	
	public void createProductRecord(ProductModel model){
		template.exchange(url, HttpMethod.POST,
				new HttpEntity<ProductModel>(model,productUtil.setHttpHeaders()),  String.class);
	}

	public ProductModel getProductRecord(long productId) {
		URI uri = UriComponentsBuilder.fromUriString(getUrl).path(Objects.toString(productId)).build().toUri();

		ProductModel model =template.getForObject(uri, ProductModel.class);
		return model;
	}
}
