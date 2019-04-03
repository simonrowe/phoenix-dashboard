package io.pivotal.pa.phoenix.web.dao;

import io.pivotal.pa.phoenix.model.Time;
import org.springframework.data.repository.CrudRepository;

public interface TimeDao extends CrudRepository<Time, Long> {
}
