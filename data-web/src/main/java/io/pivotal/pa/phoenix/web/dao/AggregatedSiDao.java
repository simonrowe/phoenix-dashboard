package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedSI;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface AggregatedSiDao extends CrudRepository<AggregatedSI, Long> {

    @Query("select new io.pivotal.pa.phoenix.web.model.CountDto(max(siCount)) from AggregatedSI")
    CountDto findMaxSIs();


    @Query("select new io.pivotal.pa.phoenix.web.model.CountDto(max(siCount)) from AggregatedSI where time.time >= :fromTime")
    CountDto findMaxSIsAfter(@Param("fromTime") Date fromTime);


    @Query("from AggregatedSI as aggSI where id = (select max(id) from AggregatedSI)")
    AggregatedSI latestSICount();
}
