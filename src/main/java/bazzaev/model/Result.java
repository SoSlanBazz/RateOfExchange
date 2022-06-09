package bazzaev.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.util.HashMap;
@Getter
@Setter
public class Result {
   private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private HashMap<String, Double> rates;
}
