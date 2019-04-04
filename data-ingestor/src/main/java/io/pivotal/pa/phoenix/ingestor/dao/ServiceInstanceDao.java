package io.pivotal.pa.phoenix.ingestor.dao;

import io.pivotal.pa.phoenix.model.ServiceInstance;
import org.springframework.data.repository.CrudRepository;

public interface ServiceInstanceDao extends CrudRepository<ServiceInstance, Long> {
}
