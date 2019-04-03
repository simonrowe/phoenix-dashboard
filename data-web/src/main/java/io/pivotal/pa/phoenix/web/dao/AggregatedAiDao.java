package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AggregatedAiDao extends CrudRepository<AggregatedAI, Long> {

    @Query("select new io.pivotal.pa.phoenix.web.model.CountDto(max(aiCount)) from AggregatedAI")
    CountDto findMaxAIs();


    @Query("select new io.pivotal.pa.phoenix.web.model.CountDto(max(aiCount)) from AggregatedAI where time.time >= :fromTime")
    CountDto findMaxAIsAfter(@Param("fromTime") Date fromTime);


    @Query("from AggregatedAI as aggAI where id = (select max(id) from AggregatedAI)")
    AggregatedAI latestAICount();
}
