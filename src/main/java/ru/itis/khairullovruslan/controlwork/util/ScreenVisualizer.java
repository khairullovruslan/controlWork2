package ru.itis.khairullovruslan.controlwork.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import ru.itis.khairullovruslan.controlwork.BotApplication;

import java.io.IOException;
import java.util.Objects;

public class ScreenVisualizer {
    public static void show(String fxmlName) {
        try {
            Stage stage = BotApplication.getStage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(ScreenVisualizer.class.getResource(fxmlName)));
            stage.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }





}
