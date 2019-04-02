package io.pivotal.pa.phoenix.datacollector.uaa.service;

import io.pivotal.pa.phoenix.datacollector.uaa.model.ProcessResponse;

public interface ProcessClient {

    ProcessResponse process(String uri);

}
