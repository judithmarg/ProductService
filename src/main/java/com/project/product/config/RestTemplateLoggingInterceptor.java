package com.project.product.config;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanKind;
import io.opentelemetry.api.trace.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logRequestDetails(request,body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response);
        return response;
    }

    private void logRequestDetails(HttpRequest request, byte[] body) throws IOException {
        logger.info("Request URI: {}", request.getURI());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request Headers: {}",  request.getHeaders());
        logger.info("Request Body: {}", new String(body, "UTF-8"));
    }

    private void logResponseDetails(ClientHttpResponse response) throws IOException {
        String responseBody = new BufferedReader(new InputStreamReader(response.getBody()))
                .lines()
                .collect(Collectors.joining("\n"));

        logger.info("Status Code: {}", response.getStatusCode());
        logger.info("Status Text: {}", response.getStatusText());
        logger.info("Headers: {}", response.getHeaders());
        logger.info("Response Body: {}", responseBody);
    }
}
