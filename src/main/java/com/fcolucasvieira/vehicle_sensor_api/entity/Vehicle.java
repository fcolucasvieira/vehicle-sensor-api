package com.fcolucasvieira.vehicle_sensor_api.entity;

public enum Vehicle {
    MOTO,
    CARRO,
    ONIBUS,
    CAMINHAO;

    public static Vehicle from(String value) {
        if (value == null || value.isBlank())
            throw new IllegalArgumentException("Vehicle can't be null or blank");

        try {
            return Vehicle.valueOf(value.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Unknown vehicle: " + value);
        }
    }
}
