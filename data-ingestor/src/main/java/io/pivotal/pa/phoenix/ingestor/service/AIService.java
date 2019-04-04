package io.pivotal.pa.phoenix.ingestor.service;

import io.pivotal.pa.phoenix.ingestor.dao.ApplicationInstanceDao;
import io.pivotal.pa.phoenix.model.ApplicationInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class AIService {

    @Autowired
    private ApplicationInstanceDao applicationInstanceDao;

    @Transactional
    public void save(List<ApplicationInstance> applicationInstances) {
        applicationInstanceDao.saveAll(applicationInstances);
    }

}
