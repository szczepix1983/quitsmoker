package com.szczepix.quitsmoker.enums;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HealthProgressTypeTest {

    @Test
    public void getDuration() {
        HealthProgressType[] types = HealthProgressType.class.getEnumConstants();
        for (HealthProgressType type : types) {
            assertThat(type.getDuration()).isNotNull();
            assertThat(type.getDuration()).isGreaterThan(0);
        }
    }

    @Test
    public void getDescription() {
        HealthProgressType[] types = HealthProgressType.class.getEnumConstants();
        for (HealthProgressType type : types) {
            assertThat(type.getDescription()).isNotNull();
            assertThat(type.getDescription()).isNotEmpty();
        }
    }
}