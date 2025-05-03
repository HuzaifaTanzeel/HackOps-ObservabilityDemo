package com.hackops.observabilitydemo.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/demo")
public class ErrorDemoController {

    private static final Logger logger = LoggerFactory.getLogger(ErrorDemoController.class);
    private final Counter errorCounter;
    private final Counter timeoutCounter;
    private final Counter notFoundCounter;

    public ErrorDemoController(MeterRegistry registry) {
        this.errorCounter = registry.counter("demo.errors", "type", "generic");
        this.timeoutCounter = registry.counter("demo.errors", "type", "timeout");
        this.notFoundCounter = registry.counter("demo.errors", "type", "notfound");
    }

    @GetMapping("/error")
    public String triggerError() {
        logger.error("Simulating a generic error");
        errorCounter.increment();
        throw new RuntimeException("This is a simulated error");
    }

    @GetMapping("/timeout")
    public String triggerTimeout() throws InterruptedException {
        logger.warn("Simulating a slow request that will timeout");
        timeoutCounter.increment();
        Thread.sleep(5000);
        return "This endpoint is slow!";
    }

    @GetMapping("/notfound/{id}")
    public String triggerNotFound(@PathVariable String id) {
        logger.error("Resource not found: {}", id);
        notFoundCounter.increment();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found: " + id);
    }
}