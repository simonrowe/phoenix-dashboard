package io.pivotal.pa.phoenix.collector.capi.service;

import io.pivotal.pa.phoenix.collector.capi.model.ProcessResponse;

public interface ProcessClient {

    ProcessResponse process(String uri);

}
