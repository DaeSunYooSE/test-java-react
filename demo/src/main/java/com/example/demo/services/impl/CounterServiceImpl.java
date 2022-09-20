package com.example.demo.services.impl;

import com.example.demo.domain.Counter;
import com.example.demo.domain.CounterRepository;
import com.example.demo.services.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterRepository counterRepository;


    @Override
    public void addCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username);
        c.setCounterNum(c.getCounterNum() + 1);
        counterRepository.save(c);
    }

    @Override
    public void minusCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username);
        c.setCounterNum(c.getCounterNum() - 1);
        counterRepository.save(c);
    }

    @Override
    public Integer getCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username);
        return c.getCounterNum();
    }

    @Override
    public void createCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username);
        if (c == null) {
            c = new Counter();
            c.setUsername(username);
            c.setCounterNum(0);
            counterRepository.save(c);
        }
    }
}
