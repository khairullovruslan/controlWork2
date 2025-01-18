package ru.itis.khairullovruslan.controlwork.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.khairullovruslan.controlwork.dto.WeatherDTO;
import ru.itis.khairullovruslan.controlwork.util.HttpClient;
import ru.itis.khairullovruslan.controlwork.util.HttpClientImpl;

import java.util.HashMap;


public class OpenWeatherMapService {
    private static final OpenWeatherMapService INSTANCE = new OpenWeatherMapService();
    private static final String KEY = "ec6ae61f58c44f36d3bd7d4f99c9993a";
    private final HttpClient httpClient = HttpClientImpl.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static OpenWeatherMapService getInstance() {
        return INSTANCE;
    }

    private OpenWeatherMapService() {
    }

    public WeatherDTO getWeather(String cityName) throws JsonProcessingException {


        HashMap<String, String> params = new HashMap<>();
        params.put("q", cityName);
        params.put("appid", KEY);
        params.put("units", "metric");
        String response = httpClient.get("https://api.openweathermap.org/data/2.5/weather", new HashMap<>(), params);
        return objectMapper.readValue(response, WeatherDTO.class);

    }


}
