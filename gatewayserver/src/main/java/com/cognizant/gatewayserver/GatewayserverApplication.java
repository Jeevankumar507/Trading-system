package com.cognizant.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	/*
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
	    return routeLocatorBuilder.routes()

	        // Trading Company Service
	        .route("trading_company_route", r -> r
	            .path("/trading/company/**")
	            .filters(f -> f
	                .rewritePath("/trading/company/(?<segment>.*)", "/${segment}")
	                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
	            .uri("lb://TRADING-COMPANY"))

	        // Trader Service
	        .route("trader_route", r -> r
	            .path("/trading/trader/**")
	            .filters(f -> f
	                .rewritePath("/trading/trader/(?<segment>.*)", "/${segment}"))
	            .uri("lb://TRADER"))

	        // Trade Orders Service
	        .route("trade_orders_route", r -> r
	            .path("/trading/orders/**")
	            .filters(f -> f
	                .rewritePath("/trading/orders/(?<segment>.*)", "/${segment}"))
	            .uri("lb://TRADE-ORDERS"))

	        // Asset Service
	        .route("asset_route", r -> r
	            .path("/trading/assets/**")
	            .filters(f -> f
	                .rewritePath("/trading/assets/(?<segment>.*)", "/${segment}"))
	            .uri("lb://ASSET"))

	        // Settlement Service
	        .route("settlement_route", r -> r
	            .path("/trading/settlements/**")
	            .filters(f -> f
	                .rewritePath("/trading/settlements/(?<segment>.*)", "/${segment}"))
	            .uri("lb://SETTLEMENT"))

	        .build();
	}
	*/

}
