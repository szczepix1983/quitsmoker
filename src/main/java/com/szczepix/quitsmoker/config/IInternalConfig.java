package com.szczepix.quitsmoker.config;

import com.szczepix.quitsmoker.managers.IStageManager;
import javafx.stage.Stage;
import org.springframework.scheduling.TaskScheduler;

import java.util.concurrent.Executor;

public interface IInternalConfig {

    TaskScheduler taskScheduler();

    Executor taskExecutor();

    IStageManager stageManager(final Stage stage);
}
