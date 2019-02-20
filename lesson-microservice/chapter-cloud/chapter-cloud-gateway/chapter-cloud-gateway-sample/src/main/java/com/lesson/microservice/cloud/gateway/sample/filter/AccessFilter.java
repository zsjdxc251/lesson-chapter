package com.lesson.microservice.cloud.gateway.sample.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhengshijun
 * @version created on 2019/1/24.
 */

@Component
public class AccessFilter implements GlobalFilter {
	private final static Logger log = LoggerFactory.getLogger(AccessFilter.class);

	private final static long DEFAULT_USER_ID = 1L;

	private final static String HEADER_NAME_USER_ID = "uid";


	@Override
	public Mono<Void> filter(ServerWebExchange exchange,
			GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getPath().value();
		String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
		log.info("path:{},authentication:{}", path, authentication);


		request = request.mutate().header(HEADER_NAME_USER_ID, String.valueOf(DEFAULT_USER_ID)).build();


		ServerWebExchange extExchange = exchange.mutate().request(request).build();


		return chain.filter(extExchange);
	}
}
