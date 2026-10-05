package com.fcolucasvieira.vehicle_sensor_api.repository;

import com.fcolucasvieira.vehicle_sensor_api.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DetectionRepository extends JpaRepository<Detection, UUID> {
}
