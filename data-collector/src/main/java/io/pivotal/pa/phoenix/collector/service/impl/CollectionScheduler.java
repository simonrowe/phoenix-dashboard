package io.pivotal.pa.phoenix.collector.service.impl;

import io.pivotal.pa.phoenix.collector.service.ProcessCollector;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class CollectionScheduler {

    @Autowired
    private ProcessCollector processCollector;

    @Autowired
    @Qualifier("processUriBuilder")
    private CapiUriBuilder processUriBuilder;

    @Scheduled(cron = "${scheduler.cronExpression}")
    public void collect() {
        log.info("Running collect at :" + new Date());
        processCollector.collectAndSend(processUriBuilder.build());
    }
}
