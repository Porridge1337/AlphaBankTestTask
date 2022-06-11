package ru.currencygif.dailycurrensygif.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${giphy.rich}")
    private String rich;
    @Value("${giphy.broke}")
    private String broke;
    @Value("${giphy.error}")
    private String error;
    @Value("${giphy.zero}")
    private String nothing;


    @GetMapping("/getAllTicker")
    public ResponseEntity<List> getTickers() {
        List<String> currencyInfo = currencyService.getCurrencyTicker();
        return ResponseEntity.ok(currencyInfo);
    }

    @GetMapping("/tst/{ticker}")
    public ResponseEntity<GiphyDto> getGifByTag(@PathVariable String ticker) {
        int result = currencyService.getCurrencyComparisonResult(ticker);
        GiphyDto giphyDto;
        switch (result) {
            case 1 -> giphyDto = giphyInfoService.getRandomGif(rich);
            case 0 -> giphyDto = giphyInfoService.getRandomGif(nothing);
            case -1 -> giphyDto = giphyInfoService.getRandomGif(broke);
            default -> giphyDto = giphyInfoService.getRandomGif(error);
        }
        return ResponseEntity.ok(giphyDto);
    }

}
