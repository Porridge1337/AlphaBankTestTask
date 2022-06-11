package ru.currencygif.dailycurrensygif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGifDto {

    private String type;
    private URL url;
    private String title;
    private ImagesGifDto images;
}
