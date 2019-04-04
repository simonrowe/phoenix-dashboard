package io.pivotal.pa.phoenix.ingestor.service;

import io.pivotal.pa.phoenix.ingestor.dao.ServiceInstanceDao;
import io.pivotal.pa.phoenix.model.ServiceInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class SIService {

    @Autowired
    private ServiceInstanceDao serviceInstanceDao;

    @Transactional
    public void save(List<ServiceInstance> serviceInstances) {
       serviceInstanceDao.saveAll(serviceInstances);
    }

}
