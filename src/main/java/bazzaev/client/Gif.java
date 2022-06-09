package bazzaev.client;

import bazzaev.model.JsonGif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GifFeign", url = "api.giphy.com/v1/gifs")
public interface Gif {
    @GetMapping("/search")
    JsonGif exchange(@RequestParam("api_key") String api_key, @RequestParam("q") String q,
                     @RequestParam("limit") Integer limit);

}
