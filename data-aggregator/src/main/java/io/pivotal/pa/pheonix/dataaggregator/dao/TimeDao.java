package io.pivotal.pa.pheonix.dataaggregator.dao;

import io.pivotal.pa.pheonix.dataaggregator.model.Time;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface TimeDao extends CrudRepository<Time, Long> {

    @Modifying
    @Query("update ApplicationInstance set time = :time where time is null")
    @Transactional
    void associateTime(@Param("time") Time time);
}
