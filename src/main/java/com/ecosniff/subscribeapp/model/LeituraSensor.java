package com.ecosniff.subscribeapp.model;

import com.ecosniff.subscribeapp.dto.LeituraSensorMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity(name = "leitura_sensor")
@Getter @Setter @NoArgsConstructor
public class LeituraSensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "endereco_mac", length = 17, nullable = false)
    private String enderecoMac;

    @Column(name = "mq135_co2_cov")
    private Float co2Cov;

    @Column(name = "mq4_ch4")
    private Float ch4;

    @Column(name = "mq137_nh3")
    private Float nh3;

    @Column(name = "mq136_h2s")
    private Float h2s;

    @Column(name = "cn7_n2o")
    private Float n2o;

    @Column(name = "dht_umidade")
    private Float umidade;

    @Column(name = "dht_temperatura")
    private Float temperatura;

    @Column(name = "data_hora")
    private ZonedDateTime dataHora;

    LeituraSensor(LeituraSensorMessage message){
        this.enderecoMac = message.mac();
        this.co2Cov = message.co2();
        this.ch4 = message.ch4();
        this.nh3 = message.nh3();
        this.h2s = message.h2s();
        this.n2o = message.n2o();
        this.umidade = message.umidade();
        this.temperatura = message.temperatura();
        this.dataHora = ZonedDateTime.now();
    }

    @Override
    public String toString(){
        return String.format("""
                LeituraSensor {
                    endereco_mac: %s,
                    co2: %.2f,
                    ch4: %.2f,
                    nh3: %.2f,
                    h2s: %.2f,
                    n2o: %.2f,
                    umidade: %.2f,
                    temperatura: %.2f,
                    data_hora: %s,
                }
                """, this.enderecoMac, this.co2Cov, this.ch4,
                this.nh3, this.h2s, this.n2o, this.umidade, this.temperatura,
                this.dataHora.toString());
    }
}
