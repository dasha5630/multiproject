package com.tdv.tech.multiproject.consumer;

import java.io.IOException;

public interface Consumer {

    void consume(String message) throws Exception;
}
