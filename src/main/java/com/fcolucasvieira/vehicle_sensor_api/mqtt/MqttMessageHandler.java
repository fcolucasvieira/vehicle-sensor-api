package com.fcolucasvieira.vehicle_sensor_api.mqtt;

import com.fcolucasvieira.vehicle_sensor_api.dto.MqttResponse;
import com.fcolucasvieira.vehicle_sensor_api.usecase.ProcessDetectionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class MqttMessageHandler {
    private final ObjectMapper objectMapper;
    private final ProcessDetectionUseCase useCase;

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(Message<?> message) {
        String payload = new String(
                (byte[]) message.getPayload(),
                StandardCharsets.UTF_8
        );

        String topic = (String) message.getHeaders()
                .get("mqtt_receivedTopic");

        try {
            MqttResponse response = objectMapper.readValue(
                    payload,
                    MqttResponse.class
            );

            useCase.execute(topic, response);
        } catch (Exception ex) {
            System.err.println("Error processing MQTT message: " + ex.getMessage());
        }
    }
}