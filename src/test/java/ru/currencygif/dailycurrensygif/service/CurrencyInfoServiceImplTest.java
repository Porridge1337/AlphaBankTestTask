package ru.currencygif.dailycurrensygif.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.currencygif.dailycurrensygif.client.ClientOpenExchangeRates;
import ru.currencygif.dailycurrensygif.dto.CurrencyInfoDto;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@DisplayName("Сервис валют должен уметь ")
public class CurrencyInfoServiceImplTest {

    @MockBean
    private ClientOpenExchangeRates clientOpenExchangeRates;
    @Autowired
    private CurrencyInfoService currencyInfoService;
    private static CurrencyInfoDto latest;
    private static CurrencyInfoDto historical;


    @BeforeAll
    public static void init() {
        int time = 1655065230;
        latest = new CurrencyInfoDto();
        latest.setTimestamp(time);
        latest.setBase("main");
        Map<String, Double> latestMap = new HashMap<>();
        latestMap.put("ticker1", 0.1);
        latestMap.put("ticker2", 0.5);
        latestMap.put("ticker3", 1.0);
        latestMap.put("USD", 57.62);
        latestMap.put("main", 1.0);
        latest.setRates(latestMap);

        time = 1609372799;
        historical = new CurrencyInfoDto();
        historical.setTimestamp(time);
        historical.setBase("main");
        Map<String, Double> historicalMap = new HashMap<>();
        historicalMap.put("ticker1", 0.1);
        historicalMap.put("ticker2", 1.0);
        historicalMap.put("ticker3", 0.5);
        historicalMap.put("USD", 58.000001);
        historicalMap.put("main", 1.0);
        historical.setRates(historicalMap);
    }

    @Test
    @DisplayName("выдавать позитивные изменения")
    public void testGetPositive() {
        Mockito.when(clientOpenExchangeRates.getLatestCurrencyInfo("a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(latest);
        Mockito.when(clientOpenExchangeRates.getHistoricalCurrencyInfo("2022-06-09", "a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(historical);
        currencyInfoService.getCurrencyTicker();
        int result = currencyInfoService.getCurrencyComparisonResult("ticker3");
        Assertions.assertEquals(1, result);
    }

    @Test
    @DisplayName("выдавать позитивные изменения")
    public void testGetNegative() {
        Mockito.when(clientOpenExchangeRates.getLatestCurrencyInfo("a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(latest);
        Mockito.when(clientOpenExchangeRates.getHistoricalCurrencyInfo("2022-06-09", "a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(historical);
        currencyInfoService.getCurrencyTicker();
        int result = currencyInfoService.getCurrencyComparisonResult("ticker2");
        Assertions.assertEquals(-1, result);
    }

    @Test
    @DisplayName("выдавать позитивные изменения")
    public void testGetZero() {
        Mockito.when(clientOpenExchangeRates.getLatestCurrencyInfo("a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(latest);
        Mockito.when(clientOpenExchangeRates.getHistoricalCurrencyInfo("2022-06-09", "a33ca3b5b4c8464fb9b50e4c8f2d5754"))
                .thenReturn(historical);
        currencyInfoService.getCurrencyTicker();
        int result = currencyInfoService.getCurrencyComparisonResult("ticker1");
        Assertions.assertEquals(0, result);
    }

}
