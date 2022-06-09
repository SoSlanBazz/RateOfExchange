package bazzaev.service;

import bazzaev.client.Client;
import bazzaev.client.Gif;
import bazzaev.model.JsonGif;
import bazzaev.model.Result;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class RateOfExchangeServiceTest {
    @Autowired
    private RateOfExchangeService rateOfExchangeService;
    @MockBean
    private Client client;
    @MockBean
    private Gif gif;
    private String today = "2022-06-09" ;
    private String yesterday = "2022-06-08";
    private String app_id;
    private String symbols;
    private String base;
    private Integer limit = 1;
    private String qToday = "rich";
    private String qYesterday = "broke";
    private String api_key = "DC550g92Bs9vWCfiQPg8vljAQK3KEoIS";





    @Test
    @SuppressWarnings("unchecked")
    void exchange() {
        JsonGif jsonToday = new JsonGif();
        JsonGif jsonYesterday = new JsonGif();
        HashMap <String, Double> hash = new HashMap<>();
        HashMap <String, Double> hash2 = new HashMap<>();
        List array = new ArrayList<>();
        List array2 = new ArrayList<>();
        array.add("1");
        array2.add("2");
        jsonToday.setData(array);
        jsonYesterday.setData(array2);
        Result resultToday = new Result();
        Result resultYesterday = new Result();
        hash.put("2", 1.0);
        hash2.put("2", 0.0);
        resultToday.setRates(hash);
        resultYesterday.setRates(hash2);
        given(client.exchange(yesterday, app_id, base, symbols)).willReturn(resultYesterday);
        given(client.exchange(today, app_id, base, symbols)).willReturn(resultToday);
        given(gif.exchange(api_key, qToday, limit)).willReturn(jsonToday);
        given(gif.exchange(api_key, qYesterday, limit)).willReturn(jsonYesterday);
        JsonGif s = rateOfExchangeService.exchange(today, yesterday,app_id, symbols, base);
        assertNotNull(s);
        assertEquals(s.getData().get(0), "1");
    }

    @Test
    @SuppressWarnings("unchecked")
    void exchange2() {
        JsonGif jsonToday = new JsonGif();
        JsonGif jsonYesterday = new JsonGif();
        HashMap <String, Double> hash = new HashMap<>();
        HashMap <String, Double> hash2 = new HashMap<>();
        List array = new ArrayList<>();
        List array2 = new ArrayList<>();
        array.add("1");
        array2.add("2");
        jsonToday.setData(array);
        jsonYesterday.setData(array2);
        Result resultToday = new Result();
        Result resultYesterday = new Result();
        hash.put("1", 0.0);
        hash2.put("2", 1.0);
        resultToday.setRates(hash);
        resultYesterday.setRates(hash2);
        given(client.exchange(yesterday, app_id, base, symbols)).willReturn(resultYesterday);
        given(client.exchange(today, app_id, base, symbols)).willReturn(resultToday);
        given(gif.exchange(api_key, qToday, limit)).willReturn(jsonToday);
        given(gif.exchange(api_key, qYesterday, limit)).willReturn(jsonYesterday);
        JsonGif s = rateOfExchangeService.exchange(today, yesterday,app_id, symbols, base);
        assertNotNull(s);
        assertEquals(s.getData().get(0), "2");
    }
}