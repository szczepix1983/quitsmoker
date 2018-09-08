package com.szczepix.quitsmoker.services.settingService;

import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
public class SettingCacheTest {

    private SettingCache cache;

    @Before
    public void setUp() {
        cache = new SettingCache();
    }

    @Test
    public void update() {
        List<SettingModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isEqualTo(newList);
    }

    @Test
    public void getById() {
        assertThat(cache.getById(1)).isNull();
        List<SettingModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getById(1)).isNotNull();
    }

    @Test
    public void getCache() {
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(0);
        List<SettingModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(3);
    }

    private List<SettingModel> createList(int i) {
        List<SettingModel> list = new ArrayList<>();
        while (i > 0) {
            SettingEntity entity = new SettingEntity();
            entity.setId(list.size());
            list.add(list.size(), new SettingModel(entity, mock(EventService.class)));
            i--;
        }
        return list;
    }
}