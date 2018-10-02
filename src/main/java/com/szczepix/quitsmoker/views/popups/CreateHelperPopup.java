package com.szczepix.quitsmoker.views.popups;

import com.szczepix.quitsmoker.enums.PopupViewType;
import com.szczepix.quitsmoker.managers.IStageManager;
import com.szczepix.quitsmoker.services.helperService.IHelperService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateHelperPopup extends BasePopupView {

    @FXML
    public Button saveButton;
    @FXML
    public Label titleLabel;
    @FXML
    public TextField messageText;

    private final IHelperService helperService;

    public CreateHelperPopup(final IStageManager stageManager, final IHelperService helperService) {
        super(stageManager, PopupViewType.CREATE_HELPER);
        this.helperService = helperService;
    }

    @Override
    public void onInitalize() {
        enableButton(saveButton, this::onSaveButton);
        enableCompononet(messageText);
        titleLabel.setText(PopupViewType.CREATE_HELPER.getTitle());
    }

    private void onSaveButton(ActionEvent event) {
//        helperService.create()
        close();
    }
}
