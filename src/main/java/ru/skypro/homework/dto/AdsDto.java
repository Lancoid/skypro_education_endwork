package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsDto {

    private Integer pk;
    private Integer author;
    private String title;
    private Integer price;
    private String image;

}
