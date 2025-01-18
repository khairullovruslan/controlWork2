package ru.itis.khairullovruslan.controlwork.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import ru.itis.khairullovruslan.controlwork.service.ExchangeRatesService;
import ru.itis.khairullovruslan.controlwork.service.OpenWeatherMapService;
import ru.itis.khairullovruslan.controlwork.util.ScreenVisualizer;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class BotController implements Initializable {

    @FXML
    private Label executeLabel;

    @FXML
    private Label welcomeLabel;

    @FXML
    private TextArea answerText;

    @FXML
    private TextField commandField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        welcomeLabel.setTextFill(Paint.valueOf("black"));
        executeLabel.setOnMouseClicked(event -> {
            try {
                executeRequest(commandField.getText());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public void executeRequest(String command) throws JsonProcessingException {
        answerText.clear();
        answerText.setEditable(false);
        String[] data = command.split(" ");
        System.out.println(Arrays.toString(data));

        if (command.equals("list")){
            answerText.appendText("Доступные команды: weather 'город', которая выводит погоду в указанном городе (как в первой контрольной)" +
                    " exchange (rate), которая выводит курс указанной валюты к рублю (использовать api для получения курса валют)" +
                    " quit, которая выводит на главную страницу");
        }
        else if (data[0].equals("quit")){
            ScreenVisualizer.show("/view/start.fxml");
        }
        else if (data.length > 1 && data[0].equals("weather")) {
            answerText.clear();
            answerText.appendText("В городе %s %s градусов".formatted(
                    data[1], String.valueOf(OpenWeatherMapService.getInstance().getWeather(data[1]).getMain().getTemp())));
        }
        else if (data.length > 1 && data[0].equals("exchange")) {
            answerText.clear();
            answerText.appendText("Ответ RUB к  %s :  %s".formatted(
                    data[1], String.valueOf(ExchangeRatesService.getInstance().getPrice(data[1]).getRates().getRub())));
        }

        System.out.println(command);

    }
}

