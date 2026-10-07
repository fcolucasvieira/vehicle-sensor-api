package com.fcolucasvieira.vehicle_sensor_api.dto;

public record MqttResponse(
        String classe,
        String sentido,
        String precisao
) {}
