package ru.currencygif.dailycurrensygif.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.currencygif.dailycurrensygif.client.ClientOpenExchangeRates;
import ru.currencygif.dailycurrensygif.dto.CurrencyInfoDto;
import ru.currencygif.dailycurrensygif.service.CurrencyInfoService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyInfoServiceImpl implements CurrencyInfoService {

    @Value("${openexchangerates.app.id}")
    private String appId;
    private final ClientOpenExchangeRates clientOpenExchangeRates;
    private CurrencyInfoDto latest;
    private CurrencyInfoDto historical;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH");

    @Override
    public int getCurrencyComparisonResult(String ticker) {
        Double latestRate = latest.getRates().get(ticker);
        Double historicalRate = historical.getRates().get(ticker);
        return latestRate != null && historicalRate != null ? Double.compare(latestRate, historicalRate) : -101;
    }

    @Override
    public List<String> getCurrencyTicker() {
        long currentTime = System.currentTimeMillis();
        updateCurrency(currentTime);
        List<String> comparedInfo = getTicker();
        return comparedInfo;
    }

    private void updateCurrency(long currentTime) {
        if (latest == null || !timeFormat.format(latest.getTimestamp() * 1000L)
                .equals(timeFormat.format(currentTime))) {
            latest = clientOpenExchangeRates.getLatestCurrencyInfo(appId);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTime);
        String currentData = dateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        String previousData = dateFormat.format(calendar.getTime());
        if (historical == null || !dateFormat.format(historical.getTimestamp() * 1000L).equals(previousData) &&
                !dateFormat.format(historical.getTimestamp() * 1000L).equals(currentData)) {
            historical = clientOpenExchangeRates.getHistoricalCurrencyInfo(previousData, appId);
        }
    }

    private List<String> getTicker() {
        List<String> tickerList = new ArrayList<>();
        tickerList.addAll(latest.getRates().keySet());
        return tickerList;
    }

}
