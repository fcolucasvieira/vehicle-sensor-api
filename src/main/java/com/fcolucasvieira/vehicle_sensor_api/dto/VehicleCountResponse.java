package com.fcolucasvieira.vehicle_sensor_api.dto;

import com.fcolucasvieira.vehicle_sensor_api.entity.Vehicle;

public record VehicleCountResponse (
        Vehicle vehicle,
        long count
){
}
