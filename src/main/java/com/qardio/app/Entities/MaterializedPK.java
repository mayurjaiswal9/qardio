package com.qardio.app.Entities;

import lombok.EqualsAndHashCode;

import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;

@EqualsAndHashCode
public class MaterializedPK  implements Serializable {

    private double time;
    private Date date;


}
