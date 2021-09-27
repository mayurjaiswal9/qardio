package com.qardio.app.Entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.sql.Date;
import java.util.OptionalDouble;

@Data
public class MaterializedDaily {

    private OptionalDouble avg;
    private Date date;

    public MaterializedDaily(OptionalDouble avg, Date date) {
        this.avg = avg;
        this.date = date;
    }


}
