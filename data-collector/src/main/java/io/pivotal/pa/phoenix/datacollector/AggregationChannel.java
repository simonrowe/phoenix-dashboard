package io.pivotal.pa.phoenix.datacollector;

import java.util.List;

public interface AggregationChannel<T extends Object> {

    void send(List<T> objects);
}
