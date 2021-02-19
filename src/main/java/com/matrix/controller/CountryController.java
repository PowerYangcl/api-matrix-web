package com.matrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import com.matrix.service.ICountryService;

@RestController
@RequestMapping("/api")
public class CountryController {
    @Autowired
    private ICountryService countryService;
    
    @GetMapping(value = "/homeland")
    public String getInfo(String homeland) {
    	return countryService.homeland(homeland);
    }
}

























