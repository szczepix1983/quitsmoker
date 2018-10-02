package com.szczepix.quitsmoker.services.helperService;

import com.szczepix.quitsmoker.dao.IHelperRepository;
import com.szczepix.quitsmoker.entities.HelperEntityTest;
import com.szczepix.quitsmoker.models.HelperModel;
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

@ContextConfiguration(classes = HelperServiceTest.HelperServiceTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@RunWith(SpringJUnit4ClassRunner.class)
public class HelperServiceTest {

    @Autowired
    private IHelperService helperService;

    @Autowired
    private IHelperRepository repository;

    @Autowired
    private HelperCache cache;

    @Autowired
    private EventService eventService;

    @Before
    public void setUp() {
        when(cache.getById(1)).thenReturn(createHelperMock());
    }

    @After
    public void tearDown() {
        reset(repository);
    }

    @Test
    public void initWhenNotExists() {
        when(repository.findOne(any())).thenReturn(null);
        helperService.init();
        verify(cache, atLeast(1)).update(any());
    }

    @Test
    public void initWhenExists() {
        when(repository.findOne(any())).thenReturn(new HelperEntityTest.HelperEntityMock());
        helperService.init();
        verify(cache, atLeast(1)).update(any());
    }

    @Test
    public void getHelpers() {
        assertThat(helperService.getHelpers()).isNotNull();
    }

    private HelperModel createHelperMock() {
        return new HelperModel(new HelperEntityTest.HelperEntityMock(), eventService);
    }

    @Configuration
    static class HelperServiceTestConfiguration {

        @Bean
        public IHelperRepository repository() {
            IHelperRepository repository = mock(IHelperRepository.class);
            when(repository.findAll()).thenReturn(new ArrayList<>());
            return repository;
        }

        @Bean
        public HelperCache cache() {
            return mock(HelperCache.class);
        }

        @Bean
        public IHelperService settingService() {
            return new HelperService(repository(), cache(), eventService());
        }

        @Bean
        public EventService eventService() {
            return mock(EventService.class);
        }
    }
}