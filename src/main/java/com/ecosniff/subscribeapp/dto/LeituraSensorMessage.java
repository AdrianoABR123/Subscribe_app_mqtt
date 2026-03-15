package com.ecosniff.subscribeapp.dto;

public record LeituraSensorMessage(String mac,
                                   Float co2,
                                   Float ch4,
                                   Float nh3,
                                   Float h2s,
                                   Float n2o,
                                   Float umidade,
                                   Float temperatura) {}
