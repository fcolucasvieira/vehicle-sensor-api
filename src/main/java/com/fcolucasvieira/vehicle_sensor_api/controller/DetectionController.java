package com.fcolucasvieira.vehicle_sensor_api.controller;

import com.fcolucasvieira.vehicle_sensor_api.dto.StatisticsResponse;
import com.fcolucasvieira.vehicle_sensor_api.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detections")
public class DetectionController {
    private final StatisticsService service;

    public DetectionController(StatisticsService service) {
        this.service = service;
    }

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsResponse> getStatistics() {
        StatisticsResponse response = service.getStatistics();

        return ResponseEntity.ok(response);
    }
}
