package com.example.demo.services.impl;

import com.example.demo.domain.counter.Counter;
import com.example.demo.domain.counter.CounterRepository;
import com.example.demo.services.CounterService;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    @Transactional
    public void addCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username).get();
        //Counter updateCounter = counterRepository.save(c);
        c.setCounterNum(c.getCounterNum() + 1);
    }

    @Override
    public void minusCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username).get();
        c.setCounterNum(c.getCounterNum() - 1);
        counterRepository.save(c);
    }

    @Override
    public Integer getCounter(String username) {
        Counter c = counterRepository.findCounterByUsername(username).get();
        return c.getCounterNum();
    }

    @Override
    public void createCounter(String username) {
        Optional<Counter> c = counterRepository.findCounterByUsername(username);
        if (!c.isPresent()) {
            Counter c2 = new Counter();
            c2.setUsername(username);
            c2.setCounterNum(0);
            counterRepository.save(c2);
        }
    }
}
