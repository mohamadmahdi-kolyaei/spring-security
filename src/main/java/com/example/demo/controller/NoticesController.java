package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {

    @GetMapping(path = "/notices")
    public String getNotices(){
        return "here are the notices details from DB";
    }
}
