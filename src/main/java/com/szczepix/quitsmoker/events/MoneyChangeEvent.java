package com.szczepix.quitsmoker.events;

import com.szczepix.quitsmoker.enums.BaseEventType;
import com.szczepix.quitsmoker.services.eventService.BaseEvent;
import lombok.Getter;

@Getter
public class MoneyChangeEvent extends BaseEvent {

    public MoneyChangeEvent() {
        super(BaseEventType.SETTINGS_CHANGE);
    }
}

