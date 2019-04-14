package io.pivotal.pa.phoenix.aggregator.dao;
import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.model.Time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AggregatedSiDao extends CrudRepository<AggregatedSI, Long> {

    @Query("select new AggregatedSI(count(id)) from ServiceInstance where time = :time")
    AggregatedSI aggregate(@Param("time") Time time);

    AggregatedSI findByTime(Time time);

}
