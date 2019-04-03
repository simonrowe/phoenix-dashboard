package io.pivotal.pa.phoenix.aggregator.dao;

import io.pivotal.pa.phoenix.model.ApplicationInstance;
import io.pivotal.pa.phoenix.model.Time;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface TimeDao extends CrudRepository<Time, Long> {

    @Modifying
    @Query("update ApplicationInstance set time = :time where time is null")
    @Transactional
    void associateTime(@Param("time") Time time);

    @Query("from ApplicationInstance where time = :time")
    List<ApplicationInstance> findAIsByTime(@Param("time") Time time);
}
