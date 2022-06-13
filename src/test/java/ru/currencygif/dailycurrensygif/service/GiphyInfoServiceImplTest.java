package ru.currencygif.dailycurrensygif.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.currencygif.dailycurrensygif.client.ClientGiphy;
import ru.currencygif.dailycurrensygif.dto.DataGifDto;
import ru.currencygif.dailycurrensygif.dto.GiphyDto;
import ru.currencygif.dailycurrensygif.dto.ImagesGifDto;
import ru.currencygif.dailycurrensygif.dto.OriginalGifDto;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootTest
@DisplayName("Сервис гифок должен ")
public class GiphyInfoServiceImplTest {

    @Autowired
    private GiphyInfoService giphyInfoService;
    @MockBean
    private ClientGiphy clientGiphy;

    @Test
    @DisplayName("выдавать случайную гифку")
    public void testGetRandomGif() throws MalformedURLException {
        OriginalGifDto originalGifDto = new OriginalGifDto("tstFrame", "hash", "100", new URL("https://www.google.com/"), "395");
        ImagesGifDto imagesGifDto = new ImagesGifDto(originalGifDto);
        DataGifDto dataGifDto = new DataGifDto("gif", new URL("https://www.google.com/"), "title", imagesGifDto);
        GiphyDto giphyDto = new GiphyDto(dataGifDto);
        Mockito.when(clientGiphy.getRandomGif("jCWylEFUh9HGNpdjaNlhLDqQAmejXW7j", "test")).thenReturn(giphyDto);
        Assertions.assertEquals(giphyInfoService.getRandomGif("test"), giphyDto);
    }

}
