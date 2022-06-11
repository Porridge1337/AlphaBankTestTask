package ru.currencygif.dailycurrensygif.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.currencygif.dailycurrensygif.dto.CurrencyInfoDto;

@FeignClient(name = "OpenExchangeRatesClient", url = "${openexchangerates.url}")
public interface ClientOpenExchangeRates {

    @GetMapping("/latest.json")
    CurrencyInfoDto getLatestCurrencyInfo(@RequestParam("app_id") String appId);

    @GetMapping("/historical/{date}.json")
    CurrencyInfoDto getHistoricalCurrencyInfo(@PathVariable() String date,
                                              @RequestParam("app_id") String appId);

}
