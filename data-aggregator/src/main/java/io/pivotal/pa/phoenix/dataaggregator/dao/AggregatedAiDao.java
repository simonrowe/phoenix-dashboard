package io.pivotal.pa.phoenix.dataaggregator.dao;

import io.pivotal.pa.phoenix.dataaggregator.model.AggregatedAI;
import io.pivotal.pa.phoenix.dataaggregator.model.Time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AggregatedAiDao extends CrudRepository<AggregatedAI, Long> {


    @Query("select new AggregatedAI(sum(instances)) from ApplicationInstance where time = :time")
    AggregatedAI aggregate(@Param("time") Time time);

    AggregatedAI findByTime(Time time);
}
