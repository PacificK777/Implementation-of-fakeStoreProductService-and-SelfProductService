package com.example.projectbyumang.MyfirstAPI;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class myFirstAPI {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello Test API";
    }
}