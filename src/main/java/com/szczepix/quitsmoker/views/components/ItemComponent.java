package com.szczepix.quitsmoker.views.components;

import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.utils.DecimalUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ItemComponent extends BaseComponentView {

    private final SettingModel model;
    @FXML
    public TextField nameText;
    @FXML
    public TextField timeText;
    @FXML
    public TextField moneyText;
    @FXML
    public TextField percentageText;
    @FXML
    public ProgressIndicator percentageIndicator;
    @FXML
    public Button detailsButton;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public ItemComponent(final SettingModel model) {
        this.model = model;
    }

    @Override
    public URL path() {
        return getClass().getClassLoader().getResource("components/item.fxml");
    }

    @Override
    public void onInitalize() {
        nameText.setText("You are free!");
        setMoney(model.getStats().get("money"));
        setPercentage(model.getStats().get("percentage"));
        enableButton(detailsButton, this::onDetailsButton);
    }

    private void onDetailsButton(ActionEvent event) {

    }

    public void setMoney(final double money) {
        moneyText.setText(DecimalUtils.format(money));
    }

    public void setPercentage(final double percentage) {
        percentageIndicator.setProgress(percentage / 100);
        percentageText.setText(DecimalUtils.format(percentage));
    }

    public void setTime(final long timeDifference){
        timeText.setText(dateFormat.format(new Date(timeDifference)));
    }
}
