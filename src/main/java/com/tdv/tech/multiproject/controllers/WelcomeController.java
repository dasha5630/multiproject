package com.tdv.tech.multiproject.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        return ResponseEntity.ok("Welcome to my multiproject");
    }

    @GetMapping("/bye")
    public ResponseEntity<String> bye(){
        return ResponseEntity.ok("bye");
    }
}
