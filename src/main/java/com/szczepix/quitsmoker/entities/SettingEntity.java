package com.szczepix.quitsmoker.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "settings")
@Data
public class SettingEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private Long timestamp;
    private Double price;

    @Override
    public boolean equals(Object obj) {
        SettingEntity setting = (SettingEntity) obj;
        return super.equals(obj) || getId() == setting.getId();
    }

    @Override
    public String toString() {
        return print(id);
    }
}
