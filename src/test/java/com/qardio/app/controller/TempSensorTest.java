package com.qardio.app.controller;


import com.qardio.app.Service.MaterializedViewRefresher;
import com.qardio.app.Service.TemperatureService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TempSensorTest {

    @Autowired
    TemperatureService temperatureService;
    @Autowired
    MaterializedViewRefresher materializedViewRefresher;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Save Temperature Data")
    void saveTemperature() throws Exception{

        String postEndpoint = "http://localhost:"+port+"/saveTemperature";

        String inputJson = "[ { \"device_id\": 123, \"temperature\": 4.0, \"ts\": \"2021-09-17 07:48:32\" }  ]";

        var request = HttpRequest.newBuilder()
                .uri(URI.create(postEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();

        var client = HttpClient.newHttpClient();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertTrue(response.body().contains("success"));

        materializedViewRefresher.refresh();
    }

    @Test
    void getHourlyData() throws Exception{
        ResponseEntity<List> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/getHourlyData").
                toString(),List.class);
       assertTrue((response.getStatusCode().toString().contains("200")));
    }

    @Test
    void getDailyData() throws Exception {
        ResponseEntity<List> response = restTemplate.getForEntity(new URL("http://localhost:" + port + "/getDailyData").
                toString(),List.class);
        assertTrue((response.getStatusCode().toString().contains("200")));
    }
}