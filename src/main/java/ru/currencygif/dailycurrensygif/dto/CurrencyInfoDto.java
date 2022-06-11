package ru.currencygif.dailycurrensygif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyInfoDto {

    private Integer timestamp;
    private String base;
    private Map<String, Double> rates;
}
