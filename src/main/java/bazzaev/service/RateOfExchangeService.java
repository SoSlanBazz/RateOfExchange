package bazzaev.service;

import bazzaev.client.ExchangeClient;
import bazzaev.client.GifClient;
import bazzaev.model.JsonGif;
import bazzaev.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class RateOfExchangeService {
    private static final  String TAG_RICH = "rich";
    private static final  String TAG_BROKE = "broke";

    @Autowired
    private ExchangeClient client;

    @Autowired
    private GifClient gif;

    @Value("${apiKeyGif}")
    private String api_key;

    @Value("${limit}")
    private Integer limit;

    @Value("${app}")
    private  String app_id;

    @Value("${base}")
    private  String base;



    public JsonGif exchange(String symbols) {
        LocalDate day =  LocalDate.now();
        LocalDate yesday = day.minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = formatter.format(day);
        String yesterday = formatter.format(yesday);
        Result resultToday = client.exchange(today, app_id, base, symbols);
        Result resultYesterday = client.exchange(yesterday, app_id, base, symbols);
        Double resToday = resultToday.getRates().get(symbols);
        Double resYesterday = resultYesterday.getRates().get(symbols);
        if (resToday >= resYesterday) {
            return gif.exchange(api_key, TAG_RICH, limit);
        } else {
            return gif.exchange(api_key, TAG_BROKE, limit);
        }
    }

}
