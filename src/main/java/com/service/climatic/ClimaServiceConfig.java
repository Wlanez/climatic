package com.service.climatic;

import com.service.climatic.proveedor.AccuweatherProveedor;
import com.service.climatic.proveedor.ApiProveedor;
import com.service.climatic.proveedor.ApixuProveedor;
import com.service.climatic.proveedor.DarkskyProveedor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClimaServiceConfig {

    @Bean
    @ConditionalOnProperty(name = "activo", havingValue = "accuweather", matchIfMissing = true)
    public ApiProveedor accuweatherService() {
        return new AccuweatherProveedor();
    }

    @Bean
    @ConditionalOnProperty(name = "activo", havingValue = "apixu")
    public ApiProveedor apixuService() {
        return new ApixuProveedor();
    }

    @Bean
    @ConditionalOnProperty(name = "activo", havingValue = "darksky")
    public ApiProveedor darkskyService() {
        return new DarkskyProveedor();
    }

}
