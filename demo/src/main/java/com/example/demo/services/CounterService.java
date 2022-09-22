package com.example.demo.services;


public interface CounterService {

    void addCounter(String username);

    void minusCounter(String username);

    Integer getCounter(String username);

    void createCounter(String username);
}
