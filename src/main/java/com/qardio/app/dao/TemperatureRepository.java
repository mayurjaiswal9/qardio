package com.qardio.app.dao;

import com.qardio.app.Entities.TemperatureData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<TemperatureData, Long> {
}