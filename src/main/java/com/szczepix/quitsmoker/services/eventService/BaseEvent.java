package com.szczepix.quitsmoker.services.eventService;


import com.szczepix.quitsmoker.enums.BaseEventType;

public abstract class BaseEvent implements IBaseEvent {

    private final BaseEventType eventType;

    public BaseEvent(final BaseEventType eventType) {
        this.eventType = eventType;
    }

    public BaseEventType getEventType() {
        return eventType;
    }
}
