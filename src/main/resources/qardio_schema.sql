CREATE TABLE temperature_data (
                                  id SERIAL PRIMARY KEY,
                                  device_id INTEGER NOT NULL,
                                  temperature  DOUBLE PRECISION NOT NULL,
                                  ts TIMESTAMP without time zone default (now() at time zone 'cest') NOT NULL
);


CREATE INDEX idx_temperature_deviceid
ON temperature_data(device_id);

CREATE INDEX idx_temperature_ts
ON temperature_data(ts);


CREATE MATERIALIZED VIEW temp_data_hourly
AS
select avg(temperature),extract(hour from ts) as time,ts::timestamp::date as date
from temperature_data
group by time,date
order by date,time asc
WITH NO DATA;

CREATE UNIQUE INDEX hourly_data ON temp_data_hourly (time);

REFRESH MATERIALIZED VIEW  temp_data_hourly;
