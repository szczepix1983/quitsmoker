package com.szczepix.quitsmoker.services.helperService;

import com.szczepix.quitsmoker.entities.HelperEntity;
import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.models.HelperModel;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import com.szczepix.quitsmoker.services.settingService.SettingCache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
public class HelperCacheTest {

    private HelperCache cache;

    @Before
    public void setUp() {
        cache = new HelperCache();
    }

    @Test
    public void update() {
        List<HelperModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isEqualTo(newList);
    }

    @Test
    public void getById() {
        assertThat(cache.getById(1)).isNull();
        List<HelperModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getById(1)).isNotNull();
    }

    @Test
    public void getCache() {
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(0);
        List<HelperModel> newList = createList(3);
        cache.update(newList);
        assertThat(cache.getCache()).isNotNull();
        assertThat(cache.getCache().size()).isEqualTo(3);
    }

    private List<HelperModel> createList(int i) {
        List<HelperModel> list = new ArrayList<>();
        while (i > 0) {
            HelperEntity entity = new HelperEntity();
            entity.setId(list.size());
            list.add(list.size(), new HelperModel(entity, mock(EventService.class)));
            i--;
        }
        return list;
    }
}