package com.tdv.tech.multiproject.consumer;

import com.tdv.tech.multiproject.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.tdv.tech.multiproject.utils.Context.GROUP_ID;
import static com.tdv.tech.multiproject.utils.Context.TOPIC;

@Service
public class ConsumerImpl implements Consumer{

    public static Logger LOG = LoggerFactory.getLogger(ConsumerImpl.class);
    @Override
    @KafkaListener(topics = TOPIC, groupId = GROUP_ID)
    public void consume(String message) throws Exception {
        LOG.info(getFormat(message));
        LOG.info(CurrencyService.getCurrencyRate(message));
        System.out.println(getFormat(message));
        System.out.println(CurrencyService.getCurrencyRate(message));
    }

    private static String getFormat(String message) {
        return String.format("#### -> Consumed message -> %s", message);
    }
}
