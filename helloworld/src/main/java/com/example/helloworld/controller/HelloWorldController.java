
package com.example.helloworld.controller;

import com.example.helloworld.dto.ErrorResponse;
import com.example.helloworld.dto.HelloWorldResponse;
import com.example.helloworld.service.HelloWorldService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/hello-world")
    public ResponseEntity<?> helloWorld(
            @RequestParam(required = false) String name) {

        String message = helloWorldService.createMessage(name);

        return ResponseEntity.ok(
                new HelloWorldResponse(message));
    }
}
