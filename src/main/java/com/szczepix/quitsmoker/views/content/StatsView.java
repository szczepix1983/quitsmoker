package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.enums.BaseEventType;
import com.szczepix.quitsmoker.services.eventService.BaseEvent;
import com.szczepix.quitsmoker.services.eventService.EventService;
import com.szczepix.quitsmoker.services.settingService.ISettingService;
import com.szczepix.quitsmoker.views.FXMLView;
import com.szczepix.quitsmoker.views.components.ItemComponent;
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

    private ItemComponent component;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("DD");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(tipButton, this::onTipButton);
        fillContent();
    }

    private void fillContent() {
        try {
            component = new ItemComponent(settingService.getSettings());
            gridPane.add(component.load(), 0, 0);
        } catch (Exception e) {
            System.out.println("eeee: " + e);
        }

        eventService.addListener(BaseEventType.SETTINGS_CHANGE, this::onChange);

        setDays();
    }

    private void onTipButton(ActionEvent event) {

    }

    public void onChange(BaseEvent baseEvent) {
        Platform.runLater(()->{
            setDays();
            component.setMoney(settingService.getSettings().getStats().get("money"));
            component.setPercentage(settingService.getSettings().getStats().get("percentage"));
            component.setTime(System.currentTimeMillis() - settingService.getSettings().getEntity().getTimestamp());
        });
    }

    private void setDays(){
        totalText.setText(dateFormat.format(System.currentTimeMillis() - settingService.getSettings().getEntity().getTimestamp()));
    }
}
