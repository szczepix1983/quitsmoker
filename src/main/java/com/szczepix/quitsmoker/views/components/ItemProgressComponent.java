package com.szczepix.quitsmoker.views.components;

import com.szczepix.quitsmoker.enums.HealthProgressType;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.utils.DecimalUtils;
import com.szczepix.quitsmoker.utils.MathUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class ItemProgressComponent extends BaseComponentView implements IUpdateItemComponent {

    private final HealthProgressType healthProgressType;
    private final SettingModel model;

    @FXML
    public TextField timeText;
    @FXML
    public TextField percentageText;
    @FXML
    public ProgressBar percentageProgressBar;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("DD HH:mm:ss");

    public ItemProgressComponent(final HealthProgressType healthProgressType, final SettingModel model) {
        this.healthProgressType = healthProgressType;
        this.model = model;

        this.dateFormat.setTimeZone(TimeZone.getTimeZone("CEST"));
    }

    @Override
    public URL path() {
        return getClass().getClassLoader().getResource("components/itemProgress.fxml");
    }

    @Override
    public void onInitalize() {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(this.healthProgressType.getDescription());
        percentageProgressBar.setTooltip(tooltip);
    }

    public void update() {
        final long timeDifference = MathUtils.timeSpend(model.getEntity().getTimestamp());
        setPercentage(MathUtils.calculatePercentage(model.getEntity().getTimestamp(), healthProgressType));
        setTime(Math.max(TimeUnit.SECONDS.toMillis(healthProgressType.getDuration()) - timeDifference, 0));
    }

    private void setPercentage(final double percentage) {
        percentageProgressBar.setProgress(percentage / 100);
        percentageText.setText(DecimalUtils.format(percentage));
    }

    private void setTime(final long timeDifference) {
        timeText.setText(dateFormat.format(new Date(timeDifference)));
    }
}
