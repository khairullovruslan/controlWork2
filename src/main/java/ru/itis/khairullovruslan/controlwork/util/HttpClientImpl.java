package ru.itis.khairullovruslan.controlwork.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.StringJoiner;


public class HttpClientImpl implements HttpClient {
    private static final HttpClientImpl INSTANCE = new HttpClientImpl();
    private final ObjectMapper mapper;

    private HttpClientImpl() {
        mapper = new ObjectMapper();
    }

    public static HttpClientImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public String get(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            System.out.println(concatUrl(url, params));
            HttpURLConnection connection = setHeaders("GET",
                    (HttpURLConnection) new URL(concatUrl(url, params)).openConnection(), headers);
            String response = readResponse(connection);
            connection.disconnect();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String post(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = setHeaders("POST",
                    (HttpURLConnection) new URL(url).openConnection(), headers);
            writeData(connection, data);
            String response = readResponse(connection);
            System.out.printf("response-code : %s%n", connection.getResponseCode());
            connection.disconnect();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String put(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = setHeaders("PUT",
                    (HttpURLConnection) new URL(url).openConnection(), headers);

            writeData(connection, data);
            String response = readResponse(connection);
            System.out.printf("response-code : %s%n", connection.getResponseCode());
            connection.disconnect();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String delete(String url, Map<String, String> headers, Map<String, String> data) {
        try {
            HttpURLConnection connection = setHeaders("DELETE",
                    (HttpURLConnection) new URL(url).openConnection(), headers);
            writeData(connection, data);
            String response = readResponse(connection);
            System.out.printf("response-code : %s%n", connection.getResponseCode());
            connection.disconnect();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String readResponse(HttpURLConnection connection) throws IOException {
        if (connection != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder content = new StringBuilder();
                String input;
                while ((input = reader.readLine()) != null) {
                    content.append(input);
                }
                return content.toString();
            }
        }
        return null;
    }

    private String concatUrl(String url, Map<String, String> params) {
        StringJoiner urlJoiner = new StringJoiner("&");
        urlJoiner.add(url + "?");
        for (String param : params.keySet()) {
            urlJoiner.add(param + "=" + params.get(param));
        }
        return urlJoiner.toString();
    }

    private HttpURLConnection setHeaders(String methodName, HttpURLConnection connection, Map<String, String> headers)
            throws ProtocolException {
        for (String header : headers.keySet()) {
            connection.setRequestProperty(header, headers.get(header));
        }
        connection.setRequestMethod(methodName);
//        connection.setConnectTimeout(5000);
//        connection.setReadTimeout(5000);
        connection.setDoOutput(true);
        return connection;
    }

    private void writeData(HttpURLConnection connection, Map<String, String> data) throws JsonProcessingException {
        String jsonInput = mapper.writeValueAsString(data);
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            outputStream.write(input, 0, input.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
