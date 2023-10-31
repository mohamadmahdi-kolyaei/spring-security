package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping(path = "/Contact")
    public String SaveContactInquiryDetails(){
        return "Inquiry details are saved to the DB";
    }
}
