package com.szczepix.quitsmoker.entities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HelperEntityTest {

    private HelperEntityMock helperEntity;

    @Before
    public void setUp() {
        helperEntity = new HelperEntityMock();
    }

    @Test
    public void checkToString() {
        assertThat(helperEntity.toString()).isEqualTo("[HelperEntityMock]:[1]");
    }

    @Test
    public void getId() {
        assertThat(helperEntity.getId()).isEqualTo(1);
    }

    @Test
    public void getCounter() {
        assertThat(helperEntity.getCounter()).isEqualTo(10);
    }

    @Test
    public void getRandomize() {
        assertThat(helperEntity.getRandomize()).isEqualTo(1000);
    }

    @Test
    public void getMessage() {
        assertThat(helperEntity.getMessage()).isEqualTo("message");
    }

    @Test
    public void checkIsEqual() {
        HelperEntity anotherEntity = new HelperEntity();
        anotherEntity.setId(1);
        assertThat(anotherEntity.equals(helperEntity)).isTrue();
    }

    @Test
    public void checkIsNotEqual() {
        HelperEntity anotherEntity = new HelperEntity();
        anotherEntity.setId(2);
        assertThat(anotherEntity.equals(helperEntity)).isFalse();
    }

    public static class HelperEntityMock extends HelperEntity {

        public HelperEntityMock() {
            setId(1);
            setCounter(10);
            setRandomize(1000);
            setMessage("message");
        }
    }
}