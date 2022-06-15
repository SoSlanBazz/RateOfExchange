package bazzaev.client;

import bazzaev.model.JsonGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GifFeign", url = "${urlGifFeign}")
public interface GifClient {

    @GetMapping("/search")
    JsonGif exchange(@RequestParam("api_key") String api_key, @RequestParam("q") String tag,
                     @RequestParam("limit") Integer limit);

}
