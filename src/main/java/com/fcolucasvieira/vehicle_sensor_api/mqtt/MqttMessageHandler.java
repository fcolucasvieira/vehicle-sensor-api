package com.fcolucasvieira.vehicle_sensor_api.mqtt;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class MqttMessageHandler {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void handle(Message<?> message) {
        String payload = new String(
                (byte[]) message.getPayload(),
                StandardCharsets.UTF_8
        );

        System.out.println("=================================");
        System.out.println("Mensagem MQTT recebida!");
        System.out.println("Tópico: " +
                message.getHeaders().get("mqtt_receivedTopic"));
        System.out.println("Payload: " + payload);
        System.out.println("=================================");
    }
}