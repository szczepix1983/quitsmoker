package com.szczepix.quitsmoker.jobs;

import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.enums.PeriodType;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UpdateMoneyJobTest {

    private UpdateMoneyJob job;
    private SettingModel model;
    private SettingEntity entity;

    @Before
    public void setUp() {
        entity = mock(SettingEntity.class);
        when(entity.getPrice()).thenReturn(1000.0);
        when(entity.getTimestamp()).thenReturn(System.currentTimeMillis() - (60 * 60 * 1000));

        model = new SettingModel(entity, mock(EventService.class));
        model.getStats().put("money", 0.0);
        model.getStats().put("percentage", 0.0);

        job = new UpdateMoneyJob(model, 5);
    }

    @Test
    public void submit() throws Exception {
        job.submit();
        assertThat(model.getStats().get("money")).isEqualTo(41.6, Offset.offset(0.1));
        assertThat(model.getStats().get("percentage")).isCloseTo(0.198, Offset.offset(0.001));
    }

}