package com.szczepix.quitsmoker;

import com.szczepix.quitsmoker.config.InternalConfig;
import com.szczepix.quitsmoker.enums.AppViewType;
import com.szczepix.quitsmoker.managers.IStageManager;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({InternalConfig.class})
public class Application extends javafx.application.Application {

    protected ConfigurableApplicationContext context;

    protected IStageManager stageManager;

    public static void main(String[] args) {
        launch(Application.class, args);
    }

    @Override
    public void init() throws Exception {
        context = SpringApplication.run(Application.class);
    }

    @Override
    public void start(Stage primaryStage) {
        stageManager = context.getBean(IStageManager.class, primaryStage);
        stageManager.show(AppViewType.MAIN);
    }

    @Override
    public void stop() {
        context.stop();
    }
}
