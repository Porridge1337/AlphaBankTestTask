package ru.currencygif.dailycurrensygif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.currencygif.dailycurrensygif.dto.GiphyDto;
import ru.currencygif.dailycurrensygif.service.CurrencyInfoService;
import ru.currencygif.dailycurrensygif.service.GiphyInfoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyRestController {

    private final CurrencyInfoService currencyService;
    private final GiphyInfoService giphyInfoService;


    @GetMapping("/test")
    public ResponseEntity<List> getCurrencyAll() {
        List<String> currencyInfo = currencyService.getCurrencyTicker();
        return ResponseEntity.ok(currencyInfo);
    }

    @GetMapping("/compare/{ticker}")
    public ResponseEntity<Integer> getComparisonInfo(@PathVariable String ticker) {
        int result = currencyService.getCurrencyComparisonResult(ticker);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/tst/{tag}")
    public ResponseEntity<GiphyDto> getAllGif(@PathVariable String tag) {
        GiphyDto randomGif = giphyInfoService.getRandomGif(tag);
        return ResponseEntity.ok(randomGif);
    }

    /*@GetMapping("/currency")
    public Map<String, Integer> getComparedCurrency() {
        return service.getComparedKeysInfo();
    }*/

}
