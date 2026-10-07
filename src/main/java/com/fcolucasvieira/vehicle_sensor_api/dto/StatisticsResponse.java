package com.fcolucasvieira.vehicle_sensor_api.dto;

import java.math.BigDecimal;
import java.util.List;

public record StatisticsResponse(
        long trafficVolume,
        BigDecimal averageConfidence,
        RecentDetectionResponse lastDetection,
        List<VehicleCountResponse> vehicleClasses,
        List<DirectionCountResponse> directions,
        List<RecentDetectionResponse> recentDetections
) {
}
