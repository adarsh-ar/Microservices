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

import com.micro.productcompositeservice.model.InventoryModel;
import com.micro.productcompositeservice.util.ProductUtil;

@Repository
public class InventoryAdaptor {

	@Autowired
	@Qualifier("inventoryCreateRestemplate")
	private RestTemplate template;

	@Autowired
	private ProductUtil productUtil;

	@Value("${inventory.service.url}")
	private String createUrl;

	@Value("${inventory.service.url.get}")
	private String getUrl;

	public void createInventoryRecord(InventoryModel invModel) {
		template.exchange(createUrl, HttpMethod.POST,
				new HttpEntity<InventoryModel>(invModel, productUtil.setHttpHeaders()), String.class);
	}

	public InventoryModel getInventoryRecord(long productId) {
		URI uri = UriComponentsBuilder.fromUriString(getUrl).path(Objects.toString(productId)).build().toUri();
		InventoryModel model = template.getForObject(uri, InventoryModel.class);
		return model;
	}

	
}
