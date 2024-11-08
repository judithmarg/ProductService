package com.project.product.config;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public OtlpHttpSpanExporter otlpHttpSpanExporter(@Value("${management.otlp.tracing.endpoint}") String url) {
        return OtlpHttpSpanExporter.builder().setEndpoint(url).build();
    }

    @Bean
    public ObservedAspect observedAspect(ObservationRegistry observationRegistry)  {
        return new ObservedAspect(observationRegistry);
    }

}
