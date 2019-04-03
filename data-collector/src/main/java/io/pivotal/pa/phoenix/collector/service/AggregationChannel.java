package io.pivotal.pa.phoenix.collector.service;

import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface AggregationChannel<T extends Object> {

    @Retryable
    void send(List<T> objects);
}
