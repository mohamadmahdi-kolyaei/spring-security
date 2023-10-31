package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping(path = "/myCards")
    public String getCardsDetails(){
        return "here are the Cards details from DB";
    }
}
