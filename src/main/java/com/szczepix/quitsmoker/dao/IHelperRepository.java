package com.szczepix.quitsmoker.dao;

import com.szczepix.quitsmoker.entities.HelperEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHelperRepository extends CrudRepository<HelperEntity, Integer> {

}
