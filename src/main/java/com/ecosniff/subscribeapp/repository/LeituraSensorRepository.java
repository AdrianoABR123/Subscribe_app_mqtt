package com.ecosniff.subscribeapp.repository;

import com.ecosniff.subscribeapp.model.LeituraSensor;
import org.springframework.data.repository.CrudRepository;

public interface LeituraSensorRepository extends CrudRepository<LeituraSensor, Long> {

    boolean existsByEnderecoMac(String enderecoMac);
}
