package com.dioneybecker.msscbeerservice.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/")
@RestController
public class HealthCheckController {
    
    @GetMapping("/")
    public ResponseEntity<String> getAll() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}