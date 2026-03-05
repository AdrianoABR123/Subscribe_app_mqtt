package com.ecosniff.subscribeapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mqtt")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class MqttProperties {
    private String urlBroker;
    private String clientId;
    private String topic;
    private int qos;
    private String username;
    private String password;
}
