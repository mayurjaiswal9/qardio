package com.qardio.app.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "temperature_data")
public class TemperatureData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int device_id;
    private double temperature;
    @Transient
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="Europe/Paris")
    private Date ts;


}
