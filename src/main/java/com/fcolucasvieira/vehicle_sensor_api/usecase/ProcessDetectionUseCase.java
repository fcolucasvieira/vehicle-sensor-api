package com.fcolucasvieira.vehicle_sensor_api.usecase;

import com.fcolucasvieira.vehicle_sensor_api.dto.MqttResponse;
import com.fcolucasvieira.vehicle_sensor_api.entity.Detection;
import com.fcolucasvieira.vehicle_sensor_api.entity.Direction;
import com.fcolucasvieira.vehicle_sensor_api.entity.Vehicle;
import com.fcolucasvieira.vehicle_sensor_api.repository.DetectionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class ProcessDetectionUseCase {
    private final DetectionRepository repository;

    public ProcessDetectionUseCase(DetectionRepository repository) {
        this.repository = repository;
    }

    public void execute(String topic, MqttResponse response) {
        String device = extractDevice(topic);

        Vehicle vehicle = Vehicle.from(response.classe());

        Direction direction = Direction.from(response.sentido());

        BigDecimal confidence = new BigDecimal(response.precisao());

        Detection detection = new Detection(
                device,
                vehicle,
                direction,
                confidence,
                Instant.now()
        );

        repository.save(detection);
    }

    private String extractDevice(String topic) {
        String[] parts = topic.split("/");

        if (parts.length != 3 || !parts[2].equals("veiculos"))
            throw new IllegalArgumentException("Invalid MQTT topic: " + topic);

        return parts[1];
    }
}
