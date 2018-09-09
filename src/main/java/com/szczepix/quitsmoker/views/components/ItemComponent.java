package com.szczepix.quitsmoker.views.components;

import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.utils.DecimalUtils;
import com.szczepix.quitsmoker.utils.MathUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ItemComponent extends BaseComponentView implements IUpdateItemComponent {

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

        this.dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
    }

    @Override
    public URL path() {
        return getClass().getClassLoader().getResource("components/item.fxml");
    }

    @Override
    public void onInitalize() {
        nameText.setText("You are free!");
        enableButton(detailsButton, this::onDetailsButton);
    }

    private void onDetailsButton(ActionEvent event) {

    }

    private void setMoney(final double money) {
        moneyText.setText(DecimalUtils.format(money));
    }

    private void setPercentage(final double percentage) {
        percentageIndicator.setProgress(percentage / 100);
        percentageText.setText(DecimalUtils.format(percentage));
    }

    private void setTime(final long timeDifference){
        timeText.setText(dateFormat.format(new Date(timeDifference)));
    }

    @Override
    public void update() {
        setMoney(model.getStats().get("money"));
        setPercentage(model.getStats().get("percentage"));
        setTime(MathUtils.timeSpend(model.getEntity().getTimestamp()));
    }
}
