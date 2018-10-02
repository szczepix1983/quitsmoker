package com.szczepix.quitsmoker.views.content;

import com.szczepix.quitsmoker.enums.ContentViewType;
import com.szczepix.quitsmoker.services.settingService.ISettingService;
import com.szczepix.quitsmoker.views.FXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuView extends FXMLView {

    @FXML
    public Button statsButton;
    @FXML
    public Button settingsButton;
    @FXML
    public Button helperButton;
    @FXML
    public Button licenseButton;

    @Autowired
    private ISettingService settingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        enableButton(statsButton, this::handleStatsButton);
        enableButton(settingsButton, this::handleSettingsButton);
        enableButton(helperButton, this::handleHelperButton);
        enableButton(licenseButton, this::handleOpenLicense);
    }

    protected void handleStatsButton(ActionEvent actionEvent) {
        if (settingService.getSettings().getEntity().getPrice() > 0) {
            stageManager.show(ContentViewType.STATS, stageManager.getView().contentPane);
        }
    }

    protected void handleSettingsButton(ActionEvent actionEvent) {
        if (settingService.getSettings().getEntity().getPrice() > 0) {
            stageManager.show(ContentViewType.SETTINGS, stageManager.getView().contentPane);
        } else {
            stageManager.show(ContentViewType.CREATE_SETTING, stageManager.getView().contentPane);
        }
    }

    protected void handleHelperButton(ActionEvent actionEvent) {
        stageManager.show(ContentViewType.HELPER, stageManager.getView().contentPane);
    }

    protected void handleOpenLicense(ActionEvent actionEvent) {
        try {
            Desktop.getDesktop().browse(new URI("https://github.com/szczepix1983/quitsmoker/blob/master/LICENSE"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
