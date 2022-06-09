package bazzaev.service;

import bazzaev.client.Client;
import bazzaev.client.Gif;
import bazzaev.model.JsonGif;
import bazzaev.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RateOfExchangeService {
    @Autowired
    private Client client;
    @Autowired
    private Gif gif;
    private Double resToday;
    private Double resYesterday;
    @Value("${appGif}")
    private String api_key;
    @Value("${limit}")
    private Integer limit;
    private String qToday = "rich";
    private String qYesterday = "broke";


    public JsonGif exchange(String today, String yesterday, String app_id,
                            String base, String symbols) {
        Result resultToday = client.exchange(today, app_id, base, symbols);
        Result resultYesterday = client.exchange(yesterday, app_id, base, symbols);
        for (Map.Entry<String, Double> entry : resultToday.getRates().entrySet()) {
            resToday =  entry.getValue();
        }
        for (Map.Entry<String, Double> entry : resultYesterday.getRates().entrySet()) {
            resYesterday = entry.getValue();
        }
        if (resToday >= resYesterday) {
            return gif.exchange(api_key, qToday, limit);
        } else {
            return gif.exchange(api_key, qYesterday, limit);
        }
    }

}
