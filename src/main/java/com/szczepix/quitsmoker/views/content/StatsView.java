package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.enums.BaseEventType;
import com.szczepix.quitsmoker.enums.HealthProgressType;
import com.szczepix.quitsmoker.services.eventService.BaseEvent;
import com.szczepix.quitsmoker.services.eventService.EventService;
import com.szczepix.quitsmoker.services.settingService.ISettingService;
import com.szczepix.quitsmoker.views.FXMLView;
import com.szczepix.quitsmoker.views.components.IUpdateItemComponent;
import com.szczepix.quitsmoker.views.components.ItemComponent;
import com.szczepix.quitsmoker.views.components.ItemProgressComponent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class StatsView extends FXMLView {

    @FXML
    public TextField totalText;
    @FXML
    public Button tipButton;
    @FXML
    public GridPane gridPane;

    @Autowired
    private EventService eventService;

    @Autowired
    private ISettingService settingService;

    private List<IUpdateItemComponent> components = new ArrayList<>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("DD");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(tipButton, this::onTipButton);
        fillContent();
    }

    private void fillContent() {
        HealthProgressType[] healthProgressTypes = HealthProgressType.class.getEnumConstants();

        try {
            IUpdateItemComponent component = new ItemComponent(settingService.getSettings());
            gridPane.add(component.load(), 0, 0);
            components.add(component);

            for (int i = 0; i < healthProgressTypes.length; i++) {
                component = new ItemProgressComponent(healthProgressTypes[i], settingService.getSettings());
                gridPane.add(component.load(), 0, i + 1);
                components.add(component);
            }
        } catch (Exception e) {
            System.out.println("eeee: " + e);
        }

        eventService.addListener(BaseEventType.SETTINGS_CHANGE, this::onChange);

        setDays();
    }

    @Override
    public void destroy() {
        eventService.removeListener(BaseEventType.SETTINGS_CHANGE);
    }

    private void onTipButton(ActionEvent event) {

    }

    public void onChange(BaseEvent baseEvent) {
        System.out.println("clear");
        Platform.runLater(() -> {
            setDays();
            components.forEach(IUpdateItemComponent::update);
        });
    }

    private void setDays() {
        totalText.setText(dateFormat.format(System.currentTimeMillis() - settingService.getSettings().getEntity().getTimestamp()));
    }
}
