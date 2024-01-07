package com.tdv.tech.multiproject.producer;

import com.tdv.tech.multiproject.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import static com.tdv.tech.multiproject.utils.Context.TOPIC;

@Service
@RequiredArgsConstructor
public class ProducerImpl implements Producer{
    public static Logger LOG = LoggerFactory.getLogger(ProducerImpl.class);

    private KafkaTemplate<String, String> kafkaTemplate;
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
