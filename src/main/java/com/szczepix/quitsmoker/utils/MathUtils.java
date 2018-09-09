package com.szczepix.quitsmoker.utils;

import com.szczepix.quitsmoker.enums.HealthProgressType;
import com.szczepix.quitsmoker.enums.PeriodType;

import java.util.concurrent.TimeUnit;

public class MathUtils {

    public static double calculateMoney(final long startTime, final double value, final PeriodType periodType) {
        double moneyPerSecond = value / periodType.getDuration();
        long timestampSeconds = TimeUnit.MILLISECONDS.toSeconds(timeSpend(startTime));
        return moneyPerSecond * timestampSeconds;
    }

    public static double calculateTotalMoney(final double value, final PeriodType periodType) {
        double moneyPerSecond = value / periodType.getDuration();
        return moneyPerSecond * periodType.getTotalDuration();
    }

    public static Long timeSpend(final long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static double calculatePercentage(Long createdAt, Double value, PeriodType periodType) {
        return Math.min((calculateMoney(createdAt, value, periodType) / calculateTotalMoney(value, periodType)) * 100, 100);
    }

    public static double calculatePercentage(final long startTime, final HealthProgressType healthProgressType) {
        long timestampSeconds = TimeUnit.MILLISECONDS.toSeconds(timeSpend(startTime));
        return Math.min((timestampSeconds / (double)healthProgressType.getDuration()) * 100, 100);
    }
}
