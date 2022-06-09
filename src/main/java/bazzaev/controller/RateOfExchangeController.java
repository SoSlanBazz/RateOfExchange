package bazzaev.controller;

import bazzaev.model.JsonGif;
import bazzaev.service.RateOfExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class RateOfExchangeController {
    @Autowired
    private RateOfExchangeService rateOfExchangeService;

    @Value("${app}")
    private  String app_id;
    @Value("${base}")
    private  String base;
    @Value("${show_alternative}")
    private  boolean show_alternative;
    @Value("${prettyprint}")
    private  boolean prettyprint;

    private final static LocalDate day =  LocalDate.now();
    private final static LocalDate yesday = day.minusDays(1);
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd");
    private final static String today = formatter.format(day);
    private final static String yesterday = formatter.format(yesday);

    @GetMapping("/course")
    public JsonGif exchange(@RequestParam String symbols){
       return rateOfExchangeService.exchange(today, yesterday, app_id, base, symbols);
    }

}
