package com.ecosniff.subscribeapp.mqtt;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.Lifecycle;
import org.springframework.context.event.EventListener;
import org.springframework.integration.core.MessageProducer;
import org.springframework.stereotype.Component;

@Component
public class MqttStartup {

    @Autowired
    private MessageProducer inbound;

    @EventListener(ApplicationEvent.class)
    public void onReady(){
        ((Lifecycle) inbound).start(); // Conecta apenas quando tudo estiver pronto
    }
}
