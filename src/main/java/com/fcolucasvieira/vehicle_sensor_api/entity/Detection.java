package com.fcolucasvieira.vehicle_sensor_api.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "detections")
@Getter
public class Detection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String device;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @Column(nullable = false, precision = 5, scale = 4)
    private BigDecimal confidence;

    @Column(name = "received_at", nullable = false)
    private Instant receivedAt;

    protected Detection() {}

    public Detection(String device, Vehicle vehicle, Direction direction, BigDecimal confidence, Instant receivedAt) {
        this.device = device;
        this.vehicle = vehicle;
        this.direction = direction;
        this.confidence = confidence;
        this.receivedAt = receivedAt;
    }
}
