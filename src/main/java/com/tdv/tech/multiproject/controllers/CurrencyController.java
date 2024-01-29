package com.tdv.tech.multiproject.controllers;

import com.tdv.tech.multiproject.producer.Producer;
import com.tdv.tech.multiproject.utils.Context;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class for kafka messages testing
 * @author dasha5630
 */
@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final Producer producer;

    /**
     * Method send currency name to Kafka topic
     */
    @PostMapping(value = "/name")
    public void sendMessageToKafka(@RequestBody String currencyName){
        producer.sendMessage(Context.TOPIC, currencyName);
    }
}
