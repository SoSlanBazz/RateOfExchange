package bazzaev.client;



import bazzaev.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@FeignClient(name = "Feign",  url = "${urlExchangeClient}")
public interface ExchangeClient {

    @GetMapping("/historical/{date}.json")
     Result exchange(@PathVariable ("date") String day, @RequestParam("app_id") String app_id,
                     @RequestParam("base") String base, @RequestParam("symbols") String symbols);

}

