package com.szczepix.quitsmoker.jobs;

import com.szczepix.quitsmoker.services.futureService.IFuture;

public interface IBaseJob extends IFuture {
    boolean isReady();
}
