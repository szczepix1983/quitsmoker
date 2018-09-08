package com.szczepix.quitsmoker.events;

import com.szczepix.quitsmoker.enums.BaseEventType;
import com.szczepix.quitsmoker.services.eventService.BaseEvent;

public class EventMock extends BaseEvent {

    private String data;

    public EventMock() {
        super(BaseEventType.MOCK);
    }

    public EventMock setData(final String data) {
        this.data = data;
        return this;
    }

    public String getData() {
        return data;
    }
}
