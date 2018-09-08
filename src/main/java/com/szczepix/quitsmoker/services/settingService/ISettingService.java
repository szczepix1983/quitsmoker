package com.szczepix.quitsmoker.services.settingService;

import com.szczepix.quitsmoker.models.SettingModel;

public interface ISettingService {

    void init();

    SettingModel getSettings();

    void save();
}
