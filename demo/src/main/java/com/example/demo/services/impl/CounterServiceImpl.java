package com.example.demo.services.impl;

import com.example.demo.domain.counter.Counter;
import com.example.demo.domain.counter.CounterRepository;
import com.example.demo.services.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

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
