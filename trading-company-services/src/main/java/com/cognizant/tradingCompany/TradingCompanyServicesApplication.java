package com.cognizant.tradingCompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class TradingCompanyServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradingCompanyServicesApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trading Company Service API")
                        .version("1.0")
                        .description("API documentation for Trading Company Service"));
    }
}
