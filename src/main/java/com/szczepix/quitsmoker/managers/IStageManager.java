package com.szczepix.quitsmoker.managers;

import com.szczepix.quitsmoker.enums.AppViewType;
import com.szczepix.quitsmoker.enums.ContentViewType;
import com.szczepix.quitsmoker.views.MainView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public interface IStageManager {

    Stage getStage();

    MainView getView();

    void setView(MainView mainView);

    void show(final AppViewType view);

    void show(final ContentViewType contentViewType, final Pane pane);
}
