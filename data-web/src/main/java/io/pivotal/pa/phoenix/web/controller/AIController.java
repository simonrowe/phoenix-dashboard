package io.pivotal.pa.phoenix.web.controller;


import io.pivotal.pa.phoenix.web.dao.AggregatedAiDao;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AIController {

    @Autowired
    private AggregatedAiDao aggregatedAiDao;

    @GetMapping("ais/max")
    public CountDto maxAiInstances() {
        return aggregatedAiDao.findMaxAIs();
    }
}
