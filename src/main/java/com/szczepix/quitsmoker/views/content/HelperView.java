package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.services.eventService.EventService;
import com.szczepix.quitsmoker.services.helperService.IHelperService;
import com.szczepix.quitsmoker.views.FXMLView;
import com.szczepix.quitsmoker.views.components.IUpdateItemComponent;
import com.szczepix.quitsmoker.views.popups.CreateHelperPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HelperView extends FXMLView {

    @FXML
    public Button createButton;
    @FXML
    public GridPane gridPane;

    @Autowired
    private EventService eventService;

    @Autowired
    private IHelperService helperService;

    private List<IUpdateItemComponent> components = new ArrayList<>();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("DD");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(createButton, this::onCreateButton);
        fillContent();
    }

    private void fillContent() {
//        HealthProgressType[] healthProgressTypes = HealthProgressType.class.getEnumConstants();
//
//        try {
//            IUpdateItemComponent component = new ItemComponent(settingService.getSettings());
//            gridPane.add(component.load(), 0, 0);
//            components.add(component);
//
//            for (int i = 0; i < healthProgressTypes.length; i++) {
//                component = new ItemProgressComponent(healthProgressTypes[i], settingService.getSettings());
//                gridPane.add(component.load(), 0, i + 1);
//                components.add(component);
//            }
//        } catch (Exception e) {
//            System.out.println("eeee: " + e);
//        }
    }

    @Override
    public void destroy() {

    }

    private void onCreateButton(ActionEvent event) {
        new CreateHelperPopup(stageManager, helperService);
    }
}
