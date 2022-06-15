package bazzaev.controller;

import bazzaev.model.JsonGif;
import bazzaev.service.RateOfExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class RateOfExchangeController {

    @Autowired
    private RateOfExchangeService rateOfExchangeService;

    @GetMapping("/course")
    public JsonGif exchange(@RequestParam String symbols){
       return rateOfExchangeService.exchange(symbols);
    }

}
