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

import com.micro.productcompositeservice.model.PriceModel;
import com.micro.productcompositeservice.util.ProductUtil;

@Repository
public class PriceAdaptor {
	
	@Autowired
	@Qualifier("priceCreateRestemplate")
	private RestTemplate template;
	
	@Autowired
	private ProductUtil productUtil;
	
	@Value("${price.service.url}")
	private String url;
	
	@Value("${price.service.url.get}")
	private String getUrl;
	
	public void createPriceRecord(PriceModel model){
		template.exchange(url, HttpMethod.POST,
				new HttpEntity<PriceModel>(model,productUtil.setHttpHeaders()),  String.class);
	}
	
	public PriceModel getPriceRecord(long productId) {
		URI uri = UriComponentsBuilder.fromUriString(getUrl).path(Objects.toString(productId)).build().toUri();
		PriceModel model = template.getForObject(uri, PriceModel.class);
		
		return model;
	}

}
