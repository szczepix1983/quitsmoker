package com.szczepix.quitsmoker.enums;

import lombok.Getter;

@Getter
public enum BaseEventType {

    MOCK("TEST_EVENT"),
    VIEW_CHANGE("VIEW_CHANGE"),
    SETTINGS_CHANGE("SETTINGS_CHANGE");

    String name;

    BaseEventType(final String name) {
        this.name = name;
    }
}
