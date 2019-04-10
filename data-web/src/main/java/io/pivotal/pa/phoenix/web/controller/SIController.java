package io.pivotal.pa.phoenix.web.controller;


import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.web.dao.AggregatedSiDao;
import io.pivotal.pa.phoenix.web.model.CountDto;
import io.pivotal.pa.phoenix.web.model.SummaryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class SIController {

    @Autowired
    private AggregatedSiDao aggregatedSiDao;

    @GetMapping("sis/max")
    public CountDto maxSiInstances(@RequestParam(name= "from", required = false) Date from) {
        return (from == null) ? aggregatedSiDao.findMaxSIs() : aggregatedSiDao.findMaxSIsAfter(from);
    }

    @GetMapping("sis/summary")
    public SummaryDto siSummary() {
        AggregatedSI latest = aggregatedSiDao.latestSICount();
        CountDto maxSis = aggregatedSiDao.findMaxSIs();
        return new SummaryDto(latest.getSiCount(), latest.getTime().getTime(), maxSis.getCount());

    }
}
