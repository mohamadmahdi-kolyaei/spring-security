package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping(path = "/myLoans")
    public String getLoansDetails(){
        return "here are the Loans details from DB";
    }

}
