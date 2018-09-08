package com.szczepix.quitsmoker.events;

import com.szczepix.quitsmoker.enums.AppViewType;
import com.szczepix.quitsmoker.enums.BaseEventType;
import com.szczepix.quitsmoker.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class ViewChangeEvent extends BaseEvent {

    private final AppViewType appViewType;

    public ViewChangeEvent(final AppViewType appViewType) {
        super(BaseEventType.VIEW_CHANGE);
        this.appViewType = appViewType;
    }
}
