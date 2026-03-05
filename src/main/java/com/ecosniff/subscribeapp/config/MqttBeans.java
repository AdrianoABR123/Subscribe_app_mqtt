package com.ecosniff.subscribeapp.config;


import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;



@Slf4j
@Configuration
@EnableIntegration
public class MqttBeans {

    private final MqttProperties properties;

    MqttBeans(MqttProperties properties){
        this.properties = properties;
    }

    @Bean(name="mqttInputChannel")
    public MessageChannel mqttInputChannel(){
        return new DirectChannel();
    }

    @Bean(name="errorChannel")
    public MessageChannel errorChannel(){return new DirectChannel();}

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory(){

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{properties.getUrlBroker()});
        options.setUserName(properties.getUsername());
        options.setPassword(properties.getPassword().toCharArray());
        options.setAutomaticReconnect(true);

        factory.setConnectionOptions(options);

        return factory;

    }
    @Bean
    public MessageProducer inbound(MqttPahoClientFactory factory){
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        properties.getClientId(),
                        factory,
                        properties.getTopic()
                );
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(properties.getQos());
        adapter.setOutputChannelName("mqttInputChannel");
        adapter.setAutoStartup(false);
        return adapter;
    }
}
