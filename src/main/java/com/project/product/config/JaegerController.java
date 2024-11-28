package com.project.product.config;

import com.mysql.cj.log.Log;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("/jaeger")
public class JaegerController {
    private ObserveService observeService = new ObserveService();
    private RestTemplate restTemplate;

    public JaegerController(ObserveService observeService, RestTemplate restTemplate) {
        this.observeService = observeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/test1")
    public ResponseEntity<String> test1() {
        final Span span = Span.current();
        try {
            String response = restTemplate.getForObject("http://localhost:8083/clients/all", String.class);
            System.out.println(response);
            int httpStatus = 200;

            span.setAttribute("http.response.headers", "Mocked-Headers"); // Personaliza con tus encabezados reales
            span.setAttribute("http.response.body", response);
            span.setAttribute("http.request.body", "Mocked-Headers");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        }
        //observeService.getActiveSpan();
        //observeService.annotation("1");
        //List<Client> response = restTemplate.getForObject("http://localhost:8083/clients/all", ArrayList.class);
        //return ResponseEntity.ok(response.toString());
    }
}
