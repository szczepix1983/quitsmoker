package com.szczepix.quitsmoker.models;

import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SettingModelTest {

    private SettingModel settingModel;

    @Before
    public void setUp() {
        SettingEntity entity = new SettingEntity();
        entity.setId(1);
        entity.setPrice(10.0);
        entity.setTimestamp(10L);
        settingModel = new SettingModel(entity, mock(EventService.class));
    }

    @Test
    public void creation() {
        assertThat(settingModel).isNotNull();
    }

    @Test
    public void getEntity() {
        assertThat(settingModel.getEntity()).isNotNull();
    }

    @Test
    public void getEventService() {
        assertThat(settingModel.getEventService()).isNotNull();
    }
}