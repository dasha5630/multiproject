package com.tdv.tech.multiproject.consumer;

public interface Consumer {

    void consume(String message) throws java.io.IOException, java.lang.InterruptedException;
}
