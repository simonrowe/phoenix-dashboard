package io.pivotal.pa.phoenix.datacollector;

public interface ProcessCollector {
    void collectAndSend();
    void collectAndSend(String uri)
;}
