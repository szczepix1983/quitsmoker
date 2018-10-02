package com.szczepix.quitsmoker.services.helperService;

import com.szczepix.quitsmoker.models.HelperModel;

import java.util.List;

public interface IHelperService {
    void init();

    List<HelperModel> getHelpers();
}
