package ru.currencygif.dailycurrensygif.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginalGifDto {

    private String frames;
    private String hash;
    private String height;
    private URL url;
    private String webp_size;

}
