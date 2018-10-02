package com.szczepix.quitsmoker.enums;

import lombok.Getter;

@Getter
public enum ContentViewType {

    MENU("Menu", "menu.fxml"),
    STATS("Stats", "stats.fxml"),
    SETTINGS("Settings", "settings.fxml"),
    CREATE_SETTING("Create", "create_settings.fxml"),
    HELPER("Helper", "helper.fxml");

    String title;
    String path;

    ContentViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}

