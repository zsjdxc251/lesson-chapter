package com.lesson.microservice.cloud.gateway.sample.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author zhengshijun
 * @version created on 2019/1/24.
 */
@Slf4j
@Component
public class CustomFilter implements WebFilter {


	@Override
	public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

		ServerHttpRequest request = exchange.getRequest();
		String path = request.getPath().value();
		log.info("CustomFilter path:{}",path);
		return chain.filter(exchange);
	}
}
