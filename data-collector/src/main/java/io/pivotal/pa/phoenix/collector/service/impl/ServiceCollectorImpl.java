package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.model.ServiceCloudControllerResponse;
import io.pivotal.pa.phoenix.collector.capi.model.Services;
import org.springframework.stereotype.Component;

@Component
public class ServiceCollectorImpl extends CollectorImpl<ServiceCloudControllerResponse, Services> {

}
