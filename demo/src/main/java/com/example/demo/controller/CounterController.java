package com.example.demo.controller;

import com.example.demo.services.impl.CounterServiceImpl;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/counter")
public class CounterController {

    private final CounterServiceImpl counterService;

    public CounterController(CounterServiceImpl counterService) {
        this.counterService = counterService;
    }


    @ResponseBody
    @PostMapping
    public void createCounter(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        counterService.createCounter(username);
    }

    @ResponseBody
    @GetMapping("/{username}")
    public Integer getCounter(@PathVariable("username") String username, HttpServletResponse res) {
        return counterService.getCounter(username);
    }

    @ResponseBody
    @PutMapping("/plus")
    public void plusCounter(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String username = body.get("username");
        counterService.addCounter(username);
    }

    @PutMapping("/minus")
    public void minusCounter(@RequestBody Map<String, String> body, HttpServletResponse response) {
        String username = body.get("username");
        counterService.minusCounter(username);
    }
}
