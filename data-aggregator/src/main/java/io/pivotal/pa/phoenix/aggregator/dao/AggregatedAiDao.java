package io.pivotal.pa.phoenix.aggregator.dao;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.model.Time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AggregatedAiDao extends CrudRepository<AggregatedAI, Long> {

    @Query("select new AggregatedAI(sum(instances)) from ApplicationInstance where time = :time")
    AggregatedAI aggregate(@Param("time") Time time);

    AggregatedAI findByTime(Time time);

}
