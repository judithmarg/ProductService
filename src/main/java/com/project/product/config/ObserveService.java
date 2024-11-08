package com.project.product.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import io.micrometer.tracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ObserveService {
    private final Random random = new Random();
    private static final Logger log = LoggerFactory.getLogger(ObserveService.class);

    @Autowired
    private ObservationRegistry registry;

    @Autowired
    private Tracer tracer;

    @Observed(name = "annotation.method", contextualName = "annotation", lowCardinalityKeyValues = {"annotation", "annotation1"})
    public String annotation(String id) {
        try {
            Thread.sleep(random.nextLong(200L)); // simulates latency
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "annotation";
    }


    // This demo the usage of observation
    public void programmatic() throws InterruptedException {
        Observation observation = Observation.createNotStarted("programmatic", this.registry).start();
        try {
            try (Observation.Scope scope = observation.openScope()) {
                Thread.sleep(random.nextLong(200L)); // simulates latency
            }
        } catch (Exception exception) {
            observation.error(exception);
            throw exception;
        } finally {
            observation.stop();
        }
    }

    public void getActiveSpan()  {
        log.info("Tracer ID " + this.tracer.currentSpan().context().spanId());
    }
}
