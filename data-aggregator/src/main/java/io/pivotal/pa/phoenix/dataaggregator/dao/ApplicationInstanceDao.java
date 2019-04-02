package io.pivotal.pa.phoenix.dataaggregator.dao;

import io.pivotal.pa.phoenix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationInstanceDao extends CrudRepository<ApplicationInstance, Long> {

    List<ApplicationInstance> findByTime(Time retrieved);
}
