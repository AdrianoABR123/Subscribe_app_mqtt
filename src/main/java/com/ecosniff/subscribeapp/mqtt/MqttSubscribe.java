package com.ecosniff.subscribeapp.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttSubscribe {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void processMessage(Message<String> message){
        try {
            String[] keyValues = {"mac", "co2", "ch4", "h2s", "n2o", "umidade", "temperatura"};

            JSONObject jsonMessage = new JSONObject(message.getPayload());
            JSONObject jsonProcessed = new JSONObject();

            for(String key : keyValues){
                if(jsonMessage.has(key)){
                    jsonProcessed.put(key, jsonMessage.opt(key));
                    continue;
                }
                jsonProcessed.put(key, null);
            }

            log.info("Topico: {} Messagem recebida sem erro: {}",
                    message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC),
                    jsonProcessed);
        }
        catch (JSONException jsonException){
            log.error("Mensagem recebida não está no formato json esperado: {}", jsonException.getMessage());
            String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
            log.error("Tópico: {}", topic);
            log.error("Mensagem recebida: {}", message.getPayload());
        }
    }
}