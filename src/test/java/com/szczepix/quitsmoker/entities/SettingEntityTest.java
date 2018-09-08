package com.szczepix.quitsmoker.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SettingEntityTest {

    private SettingEntityMock settingEntity;

    @Before
    public void setUp() {
        settingEntity = new SettingEntityMock();
    }

    @Test
    public void checkToString() {
        assertThat(settingEntity.toString()).isEqualTo("[SettingEntityMock]:[1]");
    }

    @Test
    public void getId() {
        assertThat(settingEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getPrice() {
        assertThat(settingEntity.getPrice()).isEqualTo(10.0);
    }

    @Test
    public void getTimestamp() {
        assertThat(settingEntity.getTimestamp()).isEqualTo(1000L);
    }

    @Test
    public void checkIsEqual() {
        SettingEntity anotherEntity = new SettingEntity();
        anotherEntity.setId(1);
        assertThat(anotherEntity.equals(settingEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        SettingEntity anotherEntity = new SettingEntity();
        anotherEntity.setId(2);
        assertThat(anotherEntity.equals(settingEntity)).isFalse();
    }

    public static class SettingEntityMock extends SettingEntity {

        public SettingEntityMock() {
            setId(1);
            setPrice(10.0);
            setTimestamp(1000L);
        }
    }
}