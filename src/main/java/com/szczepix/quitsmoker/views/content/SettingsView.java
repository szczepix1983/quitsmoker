package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.enums.ContentViewType;
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
public class SettingsView extends FXMLView {

    @FXML
    public Button saveButton;
    @FXML
    public Button gaveUpButton;
    @FXML
    public TextField priceText;

    @Autowired
    private ISettingService settingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(saveButton, this::onSaveButton);
        enableButton(gaveUpButton, this::onGaveUpButton);
        enableCompononet(priceText);
        setupValueText();
    }

    private void setupValueText() {
        priceText.setText("" + settingService.getSettings().getEntity().getPrice());
        priceText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                priceText.setText(oldValue);
            }
        });
    }

    private void onSaveButton(ActionEvent event) {
        String popupInfoMessage;
        try {
            settingService.getSettings().getEntity().setPrice(Double.parseDouble(priceText.getText()));
            settingService.save();

            popupInfoMessage = "The settings has been successfully updated.";
        } catch (Exception e) {
            popupInfoMessage = "Unexpected Error. \n \n " + e;
        }
        new InfoPopup(stageManager, popupInfoMessage);
    }

    private void onGaveUpButton(ActionEvent event) {
        String popupInfoMessage;
        try {
            settingService.getSettings().getEntity().setPrice(0.0);
            settingService.save();

            popupInfoMessage = "That's soo sad, your statistics has been reset, please try again as soon as it possible.";
        } catch (Exception e) {
            popupInfoMessage = "Unexpected Error. \n \n " + e;
        }
        new InfoPopup(stageManager, popupInfoMessage);

        stageManager.show(ContentViewType.CREATE_SETTING, stageManager.getView().contentPane);
    }
}

