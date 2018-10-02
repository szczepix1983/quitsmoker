package com.szczepix.quitsmoker.services.settingService;

import com.szczepix.quitsmoker.dao.ISettingRepository;
import com.szczepix.quitsmoker.entities.SettingEntity;
import com.szczepix.quitsmoker.entities.SettingEntityTest;
import com.szczepix.quitsmoker.events.MoneyChangeEvent;
import com.szczepix.quitsmoker.jobs.IJobFactory;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.eventService.EventService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = SettingServiceTest.SettingServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class SettingServiceTest {

    @Autowired
    private ISettingService settingService;

    @Autowired
    private ISettingRepository repository;

    @Autowired
    private SettingCache cache;

    @Autowired
    private EventService eventService;

    @Before
    public void setUp() {
        when(cache.getById(1)).thenReturn(createSettingsMock());
    }

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void initWhenNotExists() {
        when(repository.findOne(any())).thenReturn(null);
        settingService.init();
        verify(repository, atLeast(1)).save(any(SettingEntity.class));
        verify(cache, atLeast(1)).update(any());
    }

    @Test
    public void initWhenExists() {
        when(repository.findOne(any())).thenReturn(new SettingEntityTest.SettingEntityMock());
        settingService.init();
        verify(repository, atLeast(1)).save(any(SettingEntity.class));
        verify(cache, atLeast(1)).update(any());
    }

    @Test
    public void getSettings() {
        assertThat(settingService.getSettings()).isNotNull();
    }

    @Test
    public void save() {
        settingService.save();
        verify(repository, atLeast(1)).save(any(SettingEntity.class));
        verify(eventService, atLeast(1)).dispatch(any(MoneyChangeEvent.class));
    }

    private SettingModel createSettingsMock() {
        return new SettingModel(new SettingEntityTest.SettingEntityMock(), eventService);
    }

    @Configuration
    static class SettingServiceTestConfiguration {

        @Bean
        public ISettingRepository repository() {
            ISettingRepository repository = mock(ISettingRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public SettingCache cache() {
            return mock(SettingCache.class);
        }

        @Bean
        public ISettingService settingService() {
            return new SettingService(repository(), cache(), jobFactory(), eventService());
        }

        @Bean
        public IJobFactory jobFactory() {
            return mock(IJobFactory.class);
        }

        @Bean
        public EventService eventService() {
            return mock(EventService.class);
        }
    }
}