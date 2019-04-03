package io.pivotal.pa.phoenix.collector.service;

import java.util.List;

public interface AggregationChannel<T extends Object> {

    void send(List<T> objects);
}
