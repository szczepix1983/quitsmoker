package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.services.settingService.ISettingService;
import com.szczepix.quitsmoker.views.FXMLView;
import com.szczepix.quitsmoker.views.popups.InfoPopup;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class CreateSettingsView extends FXMLView {

    @FXML
    public TextField valueText;
    @FXML
    public Button saveButton;

    @Autowired
    private ISettingService settingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupValueText();
        enableButton(saveButton, this::onSaveButton);
    }

    private void setupValueText() {
        valueText.setText("" + settingService.getSettings().getEntity().getPrice());
        valueText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                valueText.setText(oldValue);
            }
        });
    }

    private void onSaveButton(ActionEvent event) {
        String popupInfoMessage;
        try {
            settingService.getSettings().getEntity().setTimestamp(System.currentTimeMillis());
            settingService.getSettings().getEntity().setPrice(Double.parseDouble(valueText.getText()));
            settingService.save();

            popupInfoMessage = "The settings has been successfully saved.";
        } catch (Exception e) {
            popupInfoMessage = "Unexpected Error. \n \n " + e;
        }
        new InfoPopup(stageManager, popupInfoMessage);
    }
}

