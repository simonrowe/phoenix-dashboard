package io.pivotal.pa.phoenix.collector.capi.service;

import io.pivotal.pa.phoenix.collector.capi.model.AbstractResponse;

public interface CloudControllerClient<T extends AbstractResponse> {

    T process(String uri);

}
