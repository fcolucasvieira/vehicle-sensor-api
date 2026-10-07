package com.fcolucasvieira.vehicle_sensor_api.entity;

public enum Direction {
    ENTRANDO,
    SAINDO;

    public static Direction from(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Direction can't be null or blank");

        try {
            return Direction.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown direction: " + value);
        }
    }
}
