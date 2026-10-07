package com.fcolucasvieira.vehicle_sensor_api.service;

import com.fcolucasvieira.vehicle_sensor_api.dto.DirectionCountResponse;
import com.fcolucasvieira.vehicle_sensor_api.dto.RecentDetectionResponse;
import com.fcolucasvieira.vehicle_sensor_api.dto.StatisticsResponse;
import com.fcolucasvieira.vehicle_sensor_api.dto.VehicleCountResponse;
import com.fcolucasvieira.vehicle_sensor_api.entity.Direction;
import com.fcolucasvieira.vehicle_sensor_api.entity.Vehicle;
import com.fcolucasvieira.vehicle_sensor_api.repository.DetectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class StatisticsService {
    private final DetectionRepository repository;

    public StatisticsService(DetectionRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public StatisticsResponse getStatistics() {
        long trafficVolume = repository.count();

        BigDecimal averageConfidence = repository.findAverageConfidence();

        if (averageConfidence == null)
            averageConfidence = BigDecimal.ZERO;

        RecentDetectionResponse lastDetection = repository.findLastDetection()
                .map(RecentDetectionResponse::toResponse)
                .orElse(null);

        List<VehicleCountResponse> vehicleClasses = repository.countByVehicle().stream()
                .map(proj -> new VehicleCountResponse(
                        Vehicle.from(proj.getVehicle()),
                        proj.getVehicleCount()
                ))
                .toList();

        List<DirectionCountResponse> directionsFlow = repository.countByDirection().stream()
                .map(proj -> new DirectionCountResponse(
                        Direction.from(proj.getDirection()),
                        proj.getDirectionCount()
                ))
                .toList();

        List<RecentDetectionResponse> recentDetections = repository.findRecentDetections().stream()
                .map(RecentDetectionResponse::toResponse)
                .toList();

        return new StatisticsResponse(trafficVolume, averageConfidence, lastDetection, vehicleClasses, directionsFlow, recentDetections);
    }
}
