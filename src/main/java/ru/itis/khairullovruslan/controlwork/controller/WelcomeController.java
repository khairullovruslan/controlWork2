package ru.itis.khairullovruslan.controlwork.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import ru.itis.khairullovruslan.controlwork.util.ScreenVisualizer;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML
    private Label startGameLabel;

    @FXML
    private Label welcomeLabel;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setTextFill(Paint.valueOf("black"));
        startGameLabel.setOnMouseClicked(event ->
                ScreenVisualizer.show("/view/bot.fxml"));

    }
}

