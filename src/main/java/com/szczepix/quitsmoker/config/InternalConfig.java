package com.szczepix.quitsmoker.config;

import com.szczepix.quitsmoker.managers.IStageManager;
import com.szczepix.quitsmoker.managers.StageManager;
import com.szczepix.quitsmoker.services.futureService.FutureService;
import com.szczepix.quitsmoker.services.schedulerService.SchedulerService;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executor;

@EnableScheduling
@ComponentScan("com.szczepix.quitsmoker")
@PropertySource("classpath:internal.properties")
public class InternalConfig extends AppConfig implements IInternalConfig {

    @Autowired
    private FutureService futureService;

    @Bean
    public TaskScheduler taskScheduler() {
        return new SchedulerService();
    }

    @Bean
    public Executor taskExecutor() {
        return futureService.getExecutor();
    }

    @Bean
    @Lazy
    public IStageManager stageManager(final Stage stage) {
        IStageManager stageManager = new StageManager(stage);
        return stageManager;
    }
}
