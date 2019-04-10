package io.pivotal.pa.phoenix.ingestor.controller;

import io.pivotal.pa.phoenix.ingestor.service.AIService;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AIController {

    @Autowired
    private AIService aiService;

    @PostMapping("ai/receive")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveAis(@Valid @RequestBody List<ApplicationInstance> ais) {
        aiService.save(ais);
    }

}
