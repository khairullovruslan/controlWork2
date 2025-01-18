package ru.itis.khairullovruslan.controlwork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;

public class BotApplication extends Application {

    @Getter
    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws IOException {


        stage = primaryStage;

        FXMLLoader loader = new FXMLLoader(BotApplication.class.getResource("/view/start.fxml"));
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bot");
        primaryStage.setFullScreen(true);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch();

    }
}