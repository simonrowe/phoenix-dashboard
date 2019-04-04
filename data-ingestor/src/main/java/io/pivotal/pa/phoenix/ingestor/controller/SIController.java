package io.pivotal.pa.phoenix.ingestor.controller;

import io.pivotal.pa.phoenix.ingestor.service.SIService;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SIController {

    @Autowired
    private SIService siService;

    @PostMapping("si/receive")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSis(@Valid @RequestBody List<ServiceInstance> sis) {
        siService.save(sis);
    }

}
