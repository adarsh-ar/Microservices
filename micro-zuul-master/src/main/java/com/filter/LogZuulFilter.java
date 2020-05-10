package com.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LogZuulFilter extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(LogZuulFilter.class); 

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request to %s", request.getMethod(),request.getRequestURL().toString()));
		String requestData = null;
		ObjectMapper mapper = new ObjectMapper();
	    JsonFactory factory = mapper.getFactory();
	    JsonNode requestJson = null;
		try {
            if (request.getContentLength() > 0 ) {
                requestData = CharStreams.toString(request.getReader());
            }

            if (requestData == null) {
                return null;
            }
            JsonParser parser = factory.createParser(requestData);
            requestJson = mapper.readTree(parser);
        } catch (Exception e) {
            log.error("Error parsing JSON request", e);
            return null;
        }
		log.info(String.format("%s request payload %s", request.getMethod(), requestJson.asText()));
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
