package io.pivotal.pa.phoenix.dataaggregator.service;

import io.pivotal.pa.phoenix.dataaggregator.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.dataaggregator.dao.TimeDao;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
public class AggregationScheduler {

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private AggregatedAiDao aggregatedAiDao;


    @Transactional
    public void aggregate() {
        Time time = timeDao.save(new Time(new Date()));
        timeDao.associateTime(time);
        aggregatedAiDao.aggregate(time);
    }
}
