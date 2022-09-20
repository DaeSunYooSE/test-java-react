package com.example.demo.services;

public interface CounterService {

    public void addCounter(String username);

    public void minusCounter(String username);

    public Integer getCounter(String username);

    public void createCounter(String username);
}
