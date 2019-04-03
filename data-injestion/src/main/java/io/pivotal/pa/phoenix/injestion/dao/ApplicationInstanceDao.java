package io.pivotal.pa.phoenix.injestion.dao;

import io.pivotal.pa.phoenix.model.ApplicationInstance;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationInstanceDao extends CrudRepository<ApplicationInstance, Long> {
    
}
