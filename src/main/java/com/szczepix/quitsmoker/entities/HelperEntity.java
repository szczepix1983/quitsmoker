package com.szczepix.quitsmoker.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helpers")
@Data
public class HelperEntity extends BaseEntity {

    @Id
    @GeneratedValue
    private int id;

    private String message;
    private Integer counter;
    private Integer randomize;

    @Override
    public boolean equals(Object obj) {
        HelperEntity setting = (HelperEntity) obj;
        return super.equals(obj) || getId() == setting.getId();
    }

    @Override
    public String toString() {
        return print(id);
    }
}
