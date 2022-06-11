package ru.currencygif.dailycurrensygif.service;


import java.util.List;

public interface CurrencyInfoService {

    int getCurrencyComparisonResult(String ticker);

    List<String> getCurrencyTicker();

}
