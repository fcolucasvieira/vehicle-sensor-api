package com.fcolucasvieira.vehicle_sensor_api.usecase;

import com.fcolucasvieira.vehicle_sensor_api.repository.DetectionRepository;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class ProcessDetectionUseCase {
    private final DetectionRepository repository;

    public ProcessDetectionUseCase(DetectionRepository repository) {
        this.repository = repository;
    }

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void execute(Message<?> message) {}
}
