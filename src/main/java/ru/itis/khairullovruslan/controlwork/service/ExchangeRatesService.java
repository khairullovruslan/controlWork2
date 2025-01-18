package ru.itis.khairullovruslan.controlwork.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.khairullovruslan.controlwork.dto.RateDTO;
import ru.itis.khairullovruslan.controlwork.dto.WeatherDTO;
import ru.itis.khairullovruslan.controlwork.util.HttpClient;
import ru.itis.khairullovruslan.controlwork.util.HttpClientImpl;

import java.util.HashMap;
import java.util.Optional;

public class ExchangeRatesService {
    private static final ExchangeRatesService INSTANCE = new ExchangeRatesService();
    private static final String KEY = "02c622f203fe44bbb17a3c09fa686187";
    private final HttpClient httpClient = HttpClientImpl.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public static ExchangeRatesService getInstance() {
        return INSTANCE;
    }

    private ExchangeRatesService() {
    }

    public RateDTO getPrice(String param) throws JsonProcessingException {


        HashMap<String, String> params = new HashMap<>();
        params.put("app_id", KEY);
        params.put("symbols", "RUB,%s".formatted(param));
        String response = httpClient.get("https://openexchangerates.org/api/latest.json?app_id=%s&symbols=RUB,USD"
                .formatted(KEY), new HashMap<>(), params);
        System.out.println(Optional.ofNullable(objectMapper.readValue(response, new TypeReference<>() {
        })));
        return objectMapper.readValue(response, RateDTO.class);

    }

}
