package ru.currencygif.dailycurrensygif.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.currencygif.dailycurrensygif.client.ClientGiphy;
import ru.currencygif.dailycurrensygif.dto.GiphyDto;
import ru.currencygif.dailycurrensygif.service.GiphyInfoService;

@RequiredArgsConstructor
@Service
public class GiphyInfoServiceImpl implements GiphyInfoService {

    @Value("${giphy.api}")
    private String api;
    private final ClientGiphy clientGiphy;

    @Override
    public GiphyDto getRandomGif(String tag) {
        return clientGiphy.getRandomGif(api, tag);
    }
}
