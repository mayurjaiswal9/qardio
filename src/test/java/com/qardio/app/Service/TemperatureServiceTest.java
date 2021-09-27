package com.qardio.app.Service;

import com.qardio.app.Entities.TemperatureData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TemperatureServiceTest {

    @Autowired
    TemperatureService temperatureService;
    @Autowired
    MaterializedViewRefresher materializedViewRefresher;
    @Test
    @DisplayName("Save Temperature Data")
    void saveTemperature() {
        List<TemperatureData> temperatureData = new ArrayList<>();
        TemperatureData temp = new TemperatureData();
        temp.setDevice_id(123);
        temp.setTemperature(22.1);
        temp.setTs(new Date());
        temperatureData.add(temp);
        assertEquals("success", temperatureService.saveTemp(temperatureData));
        materializedViewRefresher.refresh();
    }

    @Test
    void getHourlyData() {
        assertTrue(temperatureService.getHourlyData().size() > 0);
    }

    @Test
    void getDailyData() {
        assertTrue(temperatureService.getDailyData().size() > 0);
    }
}