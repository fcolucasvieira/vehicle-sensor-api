package com.fcolucasvieira.vehicle_sensor_api.config;

import org.eclipse.paho.mqttv5.client.MqttConnectionOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.inbound.Mqttv5PahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

import java.nio.charset.StandardCharsets;

@Configuration
@EnableIntegration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.username}")
    private String username;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.client-id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    @Bean
    public MqttConnectionOptions mqttConnectionOptions() {
        var options = new MqttConnectionOptions();

        options.setServerURIs(new String[]{broker});
        options.setUserName(username);
        options.setPassword(password.getBytes(StandardCharsets.UTF_8));

        options.setAutomaticReconnect(true);
        options.setCleanStart(true);

        return options;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer mqttInbound(
            MqttConnectionOptions mqttConnectionOptions
    ) {
        var adapter = new Mqttv5PahoMessageDrivenChannelAdapter(
                mqttConnectionOptions,
                clientId,
                topic
        );

        adapter.setOutputChannel(mqttInputChannel());

        return adapter;
    }
}