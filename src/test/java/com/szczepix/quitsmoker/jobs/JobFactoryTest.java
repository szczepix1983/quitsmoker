package com.szczepix.quitsmoker.jobs;

import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.futureService.FutureService;
import com.szczepix.quitsmoker.services.schedulerService.SchedulerService;
import com.szczepix.quitsmoker.services.settingService.SettingCache;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = JobFactoryTest.JobFactoryTestConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class JobFactoryTest {

    @Autowired
    private IJobFactory jobFactory;

    @Autowired
    private SchedulerService schedulerService;

    @After
    public void tearDown() {
        reset(schedulerService);
    }

    @Test
    public void creation() {
        assertThat(jobFactory).isNotNull();
    }

    @Test
    public void createJob() {
        SettingModel model = mock(SettingModel.class);

        jobFactory.createJob(model);
        verify(schedulerService).addJob(any(UpdateMoneyJob.class));
    }

    @Configuration
    static class JobFactoryTestConfiguration {

        @Bean
        public IJobFactory jobFactory() {
            return new JobFactory(schedulerService());
        }

        @Bean
        public FutureService futureService() {
            return mock(FutureService.class);
        }

        @Bean
        public SettingCache cache() {
            return mock(SettingCache.class);
        }

        @Bean
        public SchedulerService schedulerService() {
            return mock(SchedulerService.class);
        }
    }
}