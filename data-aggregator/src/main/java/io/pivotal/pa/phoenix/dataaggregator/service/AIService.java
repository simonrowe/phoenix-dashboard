package io.pivotal.pa.phoenix.dataaggregator.service;

import io.pivotal.pa.phoenix.dataaggregator.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.dataaggregator.dao.ApplicationInstanceDao;
import io.pivotal.pa.phoenix.dataaggregator.dao.TimeDao;
import io.pivotal.pa.phoenix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AIService {

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;


    @Scheduled(cron = "${cronExpression}")
    @Transactional
    public void aggregate() {
        Time time = timeDao.save(new Time(new Date()));
        log.info("Aggregating ai count @ " + time.getTime());
        timeDao.associateTime(time);
        aggregatedAiDao.aggregate(time);
    }

    @Transactional
    public void save(List<ApplicationInstance> applicationInstances) {
        applicationInstanceDao.saveAll(applicationInstances);
    }

    public Long maxAiCount() {
        return aggregatedAiDao.findMaxAIs();
    }
}
