package com.szczepix.quitsmoker.jobs;

import com.szczepix.quitsmoker.enums.PeriodType;
import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.futureService.IFuture;
import com.szczepix.quitsmoker.utils.MathUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class UpdateMoneyJob extends BaseJob {

    private final SettingModel model;

    public UpdateMoneyJob(final SettingModel model, final Integer fixedTime) {
        super(new AtomicInteger(fixedTime));
        this.model = model;
    }

    @Override
    public IFuture submit() throws Exception {
        updateStats();
        return super.submit();
    }

    private void updateStats() {
        SettingModel.Stats stats = model.getStats();
        final double money = MathUtils.calculateMoney(model.getEntity().getTimestamp(), model.getEntity().getPrice(), PeriodType.DAILY);
        final double percentage = MathUtils.calculatePercentage(model.getEntity().getTimestamp(), model.getEntity().getPrice(), PeriodType.DAILY);
        stats.put("money", money);
        stats.put("percentage", percentage);
        model.setStats(stats);
    }
}
