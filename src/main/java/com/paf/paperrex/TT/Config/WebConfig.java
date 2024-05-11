package com.paf.paperrex.TT.Config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;


@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    // @Bean
    // public FilterRegistrationBean corsFilter() {
    //     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //     CorsConfiguration config = new CorsConfiguration();
    //     config.setAllowCredentials(true);
    //     config.addAllowedOrigin("http://localhost:5173");
    //     config.setAllowedHeaders(
    //         Arrays.asList(
    //             HttpHeaders.AUTHORIZATION,
    //             HttpHeaders.CONTENT_TYPE,
    //             HttpHeaders.ACCEPT
    //         )
    //     );
    //     config.setAllowedMethods(
    //         Arrays.asList(
    //             HttpMethod.GET.name(),
    //             HttpMethod.POST.name(),
    //             HttpMethod.PUT.name(),
    //             HttpMethod.PATCH.name(),
    //             HttpMethod.DELETE.name(),
    //             HttpMethod.OPTIONS.name()
    //         )
    //     );
    //     config.setMaxAge(3600L);
    //     source.registerCorsConfiguration("/**", config);
    //     FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    //     bean.setOrder(-102);
    //     return bean;
    // }
}