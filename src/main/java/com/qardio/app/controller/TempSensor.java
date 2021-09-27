package com.qardio.app.controller;

import com.qardio.app.Entities.MaterializedDaily;
import com.qardio.app.Entities.MaterializedHourly;
import com.qardio.app.Entities.TemperatureData;
import com.qardio.app.Service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TempSensor {

    @Autowired
    TemperatureService temperatureService;


    @RequestMapping(path = "/saveTemperature", method = {
            RequestMethod.POST }, consumes = "application/json", produces = "application/json")
    public String saveTemperature(@RequestBody List<TemperatureData> temperatureData)
    {
        return temperatureService.saveTemp(temperatureData);
    }

    @RequestMapping(path = "/getHourlyData", method = {
            RequestMethod.GET }, produces = "application/json")
    public List<MaterializedHourly> getHourlyData()
    {
        return temperatureService.getHourlyData().stream().sorted(Comparator.comparing(MaterializedHourly::getDate))
                .collect(Collectors.toList());

    }

    @RequestMapping(path = "/getDailyData", method = {
            RequestMethod.GET }, produces = "application/json")
    public List<MaterializedDaily> getDailyData()
    {
        return temperatureService.getDailyData().stream().sorted(Comparator.comparing(MaterializedDaily::getDate))
                .collect(Collectors.toList());
    }

}
