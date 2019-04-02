package io.pivotal.pa.phoenix.dataaggregator.service;

import io.pivotal.pa.phoenix.dataaggregator.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.dataaggregator.dao.ApplicationInstanceDao;
import io.pivotal.pa.phoenix.dataaggregator.dao.TimeDao;
import io.pivotal.pa.phoenix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Component
public class AIService {

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;


    @Transactional
    public void aggregate() {
        Time time = timeDao.save(new Time(new Date()));
        timeDao.associateTime(time);
        aggregatedAiDao.aggregate(time);
    }

    @Transactional
    public void save(List<ApplicationInstance> applicationInstances) {
        applicationInstanceDao.saveAll(applicationInstances);
    }
}
