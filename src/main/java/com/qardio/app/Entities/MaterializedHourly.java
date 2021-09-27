package com.qardio.app.Entities;


import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "temp_data_hourly")
@IdClass(MaterializedPK.class)
public  class MaterializedHourly  {

    private double avg;
    @Id
    private double time;
    @Id
    private Date date;


}
