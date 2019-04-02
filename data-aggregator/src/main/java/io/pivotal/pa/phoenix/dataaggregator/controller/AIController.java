package io.pivotal.pa.phoenix.dataaggregator.controller;

import io.pivotal.pa.phoenix.dataaggregator.CountDto;
import io.pivotal.pa.phoenix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.phoenix.dataaggregator.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("application-instances")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAis(@RequestBody List<ApplicationInstance> ais) {
        aiService.save(ais);
    }

    @GetMapping("application-instance/max")
    public CountDto maxAiInstances() {
        return new CountDto(aiService.maxAiCount());
    }
}
