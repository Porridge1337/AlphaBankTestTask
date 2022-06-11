package ru.currencygif.dailycurrensygif.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.currencygif.dailycurrensygif.dto.GiphyDto;

@FeignClient(name = "GiphyClient", url = "${giphy.url}")
public interface ClientGiphy {

    @GetMapping("/random")
    GiphyDto getRandomGif(@RequestParam(name = "api_key") String api,
                          @RequestParam String tag);


}
