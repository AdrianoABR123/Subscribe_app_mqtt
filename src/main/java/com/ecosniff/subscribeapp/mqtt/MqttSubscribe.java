package com.ecosniff.subscribeapp.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttSubscribe {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void receive(Message<?> message) {
        String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
        log.info("Tópico: {}", topic);
        log.info("Mensagem recebida: {}", message.getPayload());
    }
}