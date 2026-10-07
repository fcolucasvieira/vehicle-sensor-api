package com.fcolucasvieira.vehicle_sensor_api.dto;

import com.fcolucasvieira.vehicle_sensor_api.entity.Direction;
import com.fcolucasvieira.vehicle_sensor_api.entity.Vehicle;

import java.math.BigDecimal;
import java.time.Instant;

public record RecentDetectionResponse(
        String device,
        Vehicle vehicle,
        Direction direction,
        BigDecimal confidence,
        Instant receivedAt
) {
}
