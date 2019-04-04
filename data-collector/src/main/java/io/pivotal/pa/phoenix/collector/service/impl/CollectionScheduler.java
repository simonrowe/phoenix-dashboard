package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.capi.service.impl.CapiUriBuilder;
import io.pivotal.pa.phoenix.collector.service.Collector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class CollectionScheduler {

    @Autowired
    private List<Collector> collectors;

    @Autowired
    @Qualifier("processUriBuilder")
    private CapiUriBuilder processUriBuilder;

    @Scheduled(cron = "${scheduler.cronExpression}")
    public void collect() {
        log.info("Running collect at :" + new Date());
        collectors.forEach(collector ->  collector.collectAndSend(processUriBuilder.build()));
    }
}
