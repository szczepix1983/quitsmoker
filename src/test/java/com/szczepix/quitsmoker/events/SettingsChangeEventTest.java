package com.szczepix.quitsmoker.events;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SettingsChangeEventTest {

    private MoneyChangeEvent event;

    @Before
    public void setUp() {
        event = new MoneyChangeEvent();
    }

    @Test
    public void creation() {
        assertThat(event).isNotNull();
    }
}