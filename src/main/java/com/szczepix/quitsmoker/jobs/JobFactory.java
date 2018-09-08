package com.szczepix.quitsmoker.jobs;

import com.szczepix.quitsmoker.models.SettingModel;
import com.szczepix.quitsmoker.services.schedulerService.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobFactory implements IJobFactory {

    private final SchedulerService schedulerService;

    @Autowired
    public JobFactory(final SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    public void createJob(final SettingModel model) {
        schedulerService.addJob(new UpdateMoneyJob(model, 1));
    }
}
