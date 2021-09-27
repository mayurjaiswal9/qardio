package com.qardio.app.Service;

import com.qardio.app.Entities.MaterializedDaily;
import com.qardio.app.Entities.MaterializedHourly;
import com.qardio.app.Entities.TemperatureData;
import com.qardio.app.dao.MeteralizedHourlyRepository;
import com.qardio.app.dao.TemperatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TemperatureService {

    @Autowired
    TemperatureRepository temperatureRepository;

    @Autowired
    MeteralizedHourlyRepository meteralizedHourlyRepository;

    /**
     * saveTemp is used to save the continuous stream of Temperature data feeded from Live Monitoring Device
     * using API.
     * <p>
     *This Method will save the Single or Bulk data at once and return back response as success string if all the
     * records have been saved successfully.
     * @exception Exception Input format of the data is not proper.
     * @param temperatureData List of the Temperature data to be stored.
     */

    public String saveTemp(List<TemperatureData> temperatureData) {
        List<TemperatureData> obj= temperatureRepository.saveAll(temperatureData);
        if(obj.size()> 0)
            return "success";
        else
            return "Nothing to Save";
    }

    /**
     * getHourlyData is used to return the average of continuous stream of Temperature data per Hour feeded from Live Monitoring Device
     * using API.
     * <p>
     * This Method will fetch the average data at once from Materialized Views and returns the Average per Hour. These values are precalculated
     * and already present in the tables.
     * @exception Exception Issue with Network/Database is down.
     * @response Average temperatureData List of the Temperature data to sent.
     */
    public List<MaterializedHourly> getHourlyData() {
        return this.meteralizedHourlyRepository.findAll();
    }

    /**
     * getHourlyData is used to return the average of continuous stream of Temperature data per day feeded from Live Monitoring Device
     * using API.
     * <p>
     * This Method will fetch the average data at once from Materialized Views and returns the Average per day. These values are fetched
     * by using the views and aggregating the retrieved data.
     * @exception Exception Issue with Network/Database is down.
     * @response Average temperatureData List of the Temperature data to sent.
     */

    public List<MaterializedDaily> getDailyData() {
        List<MaterializedHourly> dailyStats=getHourlyData();
        List<MaterializedDaily> result =
                dailyStats.stream().collect(
                        Collectors.groupingBy(MaterializedHourly::getDate,
                                Collectors.mapping(MaterializedHourly::getAvg, Collectors.toSet())
                        )
                ).entrySet().stream().map(e -> new MaterializedDaily(e.getValue().stream().mapToDouble(a -> a).average(),e.getKey())).collect(Collectors.toList());

        return result;
    }
}
