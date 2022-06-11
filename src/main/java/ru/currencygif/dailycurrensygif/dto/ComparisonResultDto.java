package ru.currencygif.dailycurrensygif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonResultDto {

    private Map<String, Integer> comparedCurrency;
    private GiphyDto gifInfo;

}
