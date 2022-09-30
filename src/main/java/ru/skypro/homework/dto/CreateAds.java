package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class CreateAds {

    private Integer pk;
    private String title;
    private String description;
    private String image;
    private Integer price;

}
