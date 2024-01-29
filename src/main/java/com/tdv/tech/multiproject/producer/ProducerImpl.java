package com.tdv.tech.multiproject.producer;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static com.tdv.tech.multiproject.utils.Context.TOPIC;

@Service
@RequiredArgsConstructor
public class ProducerImpl implements Producer{
    public static Logger LOG = LoggerFactory.getLogger(ProducerImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String topic, String message) {
        LOG.info(getFormat(message));
        System.out.println(getFormat(message));
        kafkaTemplate.send(TOPIC, message);
    }

    private static String getFormat(String message) {
        return String.format("#### -> Producing message -> %s", message);
    }
}
