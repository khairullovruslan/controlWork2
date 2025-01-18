package ru.itis.khairullovruslan.controlwork.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDTO {
    @JsonProperty("main")
    private Main main;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main {
        @JsonProperty("temp")
        private double temp;

    }


}