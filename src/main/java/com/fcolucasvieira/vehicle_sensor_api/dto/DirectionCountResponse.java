package com.fcolucasvieira.vehicle_sensor_api.dto;

import com.fcolucasvieira.vehicle_sensor_api.entity.Direction;

public record DirectionCountResponse(
        Direction direction,
        long count
) {
}
