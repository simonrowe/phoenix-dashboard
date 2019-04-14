package io.pivotal.pa.phoenix.aggregator.dao;

import io.pivotal.pa.phoenix.model.ServiceInstance;
import io.pivotal.pa.phoenix.model.Time;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TimeSIDao extends CrudRepository<Time, Long> {

    @Modifying
    @Query("update ServiceInstance set time = :time where time is null")
    @Transactional
    void associateTime(@Param("time") Time time);

    @Query("from ServiceInstance where time = :time")
    List<ServiceInstance> findSIsByTime(@Param("time") Time time);
}
