package com.szczepix.quitsmoker.dao;

import com.szczepix.quitsmoker.entities.SettingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISettingRepository extends CrudRepository<SettingEntity, Integer> {

}
