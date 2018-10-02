package com.szczepix.quitsmoker.models;

import com.szczepix.quitsmoker.entities.HelperEntity;
import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.events.MoneyChangeEvent;
import com.szczepix.quitsmoker.services.eventService.EventService;
import lombok.Getter;

import java.util.TreeMap;

@Getter
public class HelperModel {

    private final HelperEntity entity;
    private final EventService eventService;

    private Stats stats = new Stats();

    public HelperModel(final HelperEntity entity, final EventService eventService) {
        this.entity = entity;
        this.eventService = eventService;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
        eventService.dispatch(new MoneyChangeEvent());
    }

    public static class Stats extends TreeMap<String, Double> {

        public Stats(){
            put("money", 0.0);
            put("percentage", 0.0);
        }
    }
}
