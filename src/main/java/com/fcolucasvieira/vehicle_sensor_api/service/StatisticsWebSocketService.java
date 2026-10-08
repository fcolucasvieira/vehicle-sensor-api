package com.fcolucasvieira.vehicle_sensor_api.service;

import com.fcolucasvieira.vehicle_sensor_api.dto.StatisticsResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class StatisticsWebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    public StatisticsWebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendStatistics(StatisticsResponse statistics){
        messagingTemplate.convertAndSend(
                "/topic/statistics",
                statistics
        );
    }
}
