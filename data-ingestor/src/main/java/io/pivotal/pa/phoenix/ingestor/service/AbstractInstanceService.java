package io.pivotal.pa.phoenix.ingestor.service;

import io.pivotal.pa.phoenix.model.AbstractInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public class AbstractInstanceService<T extends AbstractInstance> {
    @Autowired
    private CrudRepository<T, Long> dao;

    @Transactional
    public void save(List<T> instances) {
        dao.saveAll(instances);
    }
}
