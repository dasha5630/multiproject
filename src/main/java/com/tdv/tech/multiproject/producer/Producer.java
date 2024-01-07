package com.tdv.tech.multiproject.producer;

public interface Producer {

    void sendMessage(String topic, String message);
}
