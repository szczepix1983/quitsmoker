package com.szczepix.quitsmoker.jobs;


import com.szczepix.quitsmoker.models.SettingModel;

public interface IJobFactory {

    void createJob(final SettingModel model);
}
