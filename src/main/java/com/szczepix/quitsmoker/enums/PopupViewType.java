package com.szczepix.quitsmoker.enums;

import lombok.Getter;

@Getter
public enum PopupViewType {

    EXCEPTION("Exception", "exception.fxml"),
    INFO("Information", "popups/infoPopup.fxml"),
    CREATE_HELPER("Create helper", "popups/createHelperPopup.fxml"),
    MOCK("Test", "mock.fxml");

    String title;
    String path;

    PopupViewType(final String title, final String path) {
        this.title = title;
        this.path = path;
    }
}
