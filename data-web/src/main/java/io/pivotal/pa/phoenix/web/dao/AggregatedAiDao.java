package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.AggregatedAI;
import io.pivotal.pa.phoenix.web.model.CountDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AggregatedAiDao extends CrudRepository<AggregatedAI, Long> {

    @Query("select new io.pivotal.pa.phoenix.web.model.CountDto(max(aiCount)) from AggregatedAI")
    CountDto findMaxAIs();
}
