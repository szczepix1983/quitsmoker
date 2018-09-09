package com.szczepix.quitsmoker.utils;

import javafx.fxml.FXMLLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.net.URL;

public class FxmlUtils {

    public static FXMLLoader load(final URL path, final AnnotationConfigApplicationContext context) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(context::getBean);
        loader.setLocation(path);
        return loader;
    }

    public static void load(final URL path, final Object controller) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setController(controller);
        fxmlLoader.setRoot(controller);
        fxmlLoader.setLocation(path);
        fxmlLoader.load();
    }
}