package io.pivotal.pa.phoenix.aggregator.service;

import io.pivotal.pa.phoenix.aggregator.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.aggregator.dao.TimeDao;
import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.Time;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Slf4j
public class AIAggregationService {

    @Autowired
    private TimeDao timeDao;

    @Autowired
    private AggregatedAiDao aggregatedAiDao;


    @Scheduled(cron = "${cronExpression}")
    @Transactional
    public void aggregate() {
        Time time = timeDao.save(new Time(new Date()));
        log.info("Aggregating AI count @ " + time.getTime());
        timeDao.associateTime(time);
        AggregatedAI aggregatedAI = aggregatedAiDao.aggregate(time);
        aggregatedAI.setTime(time);
        aggregatedAiDao.save(aggregatedAI);
        log.info("Total AI Count @ " + time.getTime() + " is " + aggregatedAI.getAiCount());
    }


}
