package ru.currencygif.dailycurrensygif.service;

import ru.currencygif.dailycurrensygif.dto.GiphyDto;

public interface GiphyInfoService {

    GiphyDto getRandomGif(String tag);
}
