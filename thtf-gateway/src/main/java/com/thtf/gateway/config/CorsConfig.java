package com.thtf.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * ---------------------------
 * CORS 跨域配置
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/2 14:35
 * 版本：  v1.0
 * ---------------------------
 */
@Configuration
public class CorsConfig {

    @Bean
    CorsWebFilter corsWebFilter() {

        // cors跨域配置对象
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("http://localhost");
        config.setAllowCredentials(true);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // 配置源对象
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // cors过滤器对象
        return new CorsWebFilter(source);
    }
}