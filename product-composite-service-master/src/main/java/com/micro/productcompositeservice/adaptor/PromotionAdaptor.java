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

import com.micro.productcompositeservice.model.PromotionModel;
import com.micro.productcompositeservice.util.ProductUtil;

@Repository
public class PromotionAdaptor {
	
	@Autowired
	@Qualifier("promotionCreateRestemplate")
	private RestTemplate template;
	
	@Autowired
	private ProductUtil productUtil;
	
	@Value("${promotion.service.url}")
	private String url;
	
	@Value("${promotion.service.url.get}")
	private String getUrl;
	
	public void createPromotionRecord(PromotionModel model){
		template.exchange(url, HttpMethod.POST,
				new HttpEntity<PromotionModel>(model,productUtil.setHttpHeaders()),  String.class);
	}

	public PromotionModel getPromotionRecord(long productId) {
		URI uri = UriComponentsBuilder.fromUriString(getUrl).path(Objects.toString(productId)).build().toUri();
		PromotionModel model =template.getForObject(uri, PromotionModel.class);
		return model;
	}
}
