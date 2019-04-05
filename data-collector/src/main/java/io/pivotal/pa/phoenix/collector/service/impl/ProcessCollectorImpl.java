package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.Process;
import io.pivotal.pa.phoenix.collector.capi.model.ProcessCloudControllerResponse;
import org.springframework.stereotype.Component;

@Component
public class ProcessCollectorImpl extends CollectorImpl<ProcessCloudControllerResponse, Process> {

}
