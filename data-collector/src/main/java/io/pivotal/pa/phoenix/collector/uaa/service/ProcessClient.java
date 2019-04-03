package io.pivotal.pa.phoenix.collector.uaa.service;

import io.pivotal.pa.phoenix.collector.uaa.model.ProcessResponse;

public interface ProcessClient {

    ProcessResponse process(String uri);

}
