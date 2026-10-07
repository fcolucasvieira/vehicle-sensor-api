package com.fcolucasvieira.vehicle_sensor_api.repository;

import com.fcolucasvieira.vehicle_sensor_api.entity.Detection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetectionRepository extends JpaRepository<Detection, UUID> {

    @Query(value = """
            SELECT AVG(confidence)
            FROM detections
            """, nativeQuery = true)
    BigDecimal findAverageConfidence();

    @Query(value = """
            SELECT *
            FROM detections
            ORDER BY received_at DESC
            LIMIT 1
            """, nativeQuery = true)
    Optional<Detection> findLastDetection();

    @Query(value = """
            SELECT vehicle, COUNT(*) AS vehicle_count
            FROM detections
            GROUP BY vehicle
            ORDER BY vehicle
            """, nativeQuery = true)
    List<VehicleCountProjection> countByVehicle();

    @Query(value = """
            SELECT direction, COUNT(*) AS direction_count
            FROM detections
            GROUP BY direction
            ORDER BY direction
            """, nativeQuery = true)
    List<DirectionCountProjection> countByDirection();

    @Query(value = """
            SELECT *
            FROM detections
            ORDER BY received_at DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Detection> findRecentDetections();

    interface VehicleCountProjection {
        String getVehicle();
        Long getVehicleCount();
    }

    interface DirectionCountProjection {
        String getDirection();
        Long getDirectionCount();
    }
}