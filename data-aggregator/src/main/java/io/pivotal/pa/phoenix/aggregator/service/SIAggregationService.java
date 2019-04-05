package io.pivotal.pa.phoenix.aggregator.service;

import io.pivotal.pa.phoenix.aggregator.dao.AggregatedSiDao;
import io.pivotal.pa.phoenix.aggregator.dao.TimeSIDao;
import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.Time;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;

@Component
@Slf4j
public class SIAggregationService {

    @Autowired
    private TimeSIDao timeSIDao;

    @Autowired
    private AggregatedSiDao aggregatedSiDao;


    @Scheduled(cron = "${cronExpression}")
    @Transactional
    public void aggregate() {
        Time time = timeSIDao.save(new Time(new Date()));
        log.info("Aggregating SI count @ " + time.getTime());
        timeSIDao.associateTime(time);
        AggregatedSI aggregatedSI = aggregatedSiDao.aggregate(time);
        aggregatedSI.setTime(time);
        aggregatedSiDao.save(aggregatedSI);
        log.info("Total SI Count @ " + time.getTime() + " is " + aggregatedSI.getSiCount());
    }


}
