package com.fcolucasvieira.vehicle_sensor_api.dto;

public record MQTTResponse(
        String classe,
        String sentido,
        String precisao
) {}
