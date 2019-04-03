package io.pivotal.pa.phoenix.web.controller;


import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.web.dao.AggregatedAiDao;
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
public class AIController {

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @GetMapping("ais/max")
    public CountDto maxAiInstances(@RequestParam(name= "from", required = false) Date from) {
        return (from == null) ? aggregatedAiDao.findMaxAIs() : aggregatedAiDao.findMaxAIsAfter(from);
    }

    @GetMapping("ais/summary")
    public SummaryDto aiSummary() {
        AggregatedAI latest = aggregatedAiDao.latestAICount();
        CountDto maxAis = aggregatedAiDao.findMaxAIs();
        return new SummaryDto(latest.getAiCount(), latest.getTime().getTime(), maxAis.getCount());

    }
}
