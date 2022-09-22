package com.example.demo.controller;

import com.example.demo.services.impl.CounterServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    private final CounterServiceImpl counterService;

    public CounterController(CounterServiceImpl counterService) {
        this.counterService = counterService;
    }

    @PostMapping
    public void createCounter(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        counterService.createCounter(username);
    }

    @GetMapping("/{username}")
    public Map<String, Object> getCounter(@PathVariable("username") String username) {
        Map<String, Object> result = new HashMap<>();
        result.put("counterNum", counterService.getCounter(username));
        return result;
    }

    @PutMapping("/plus")
    public void plusCounter(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        counterService.addCounter(username);
    }

    @PutMapping("/minus")
    public void minusCounter(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        counterService.minusCounter(username);
    }

}
