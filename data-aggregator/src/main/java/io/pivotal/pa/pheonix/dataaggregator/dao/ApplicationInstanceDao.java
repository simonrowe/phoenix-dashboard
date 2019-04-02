package io.pivotal.pa.pheonix.dataaggregator.dao;

import io.pivotal.pa.pheonix.dataaggregator.model.ApplicationInstance;
import io.pivotal.pa.pheonix.dataaggregator.model.Time;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationInstanceDao extends CrudRepository<ApplicationInstance, Long> {

    List<ApplicationInstance> findByTime(Time retrieved);
}
