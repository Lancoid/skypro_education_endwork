package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class AdsFullDto {

    private Integer pk;
    private Long author_id;
    private String authorFirstName;
    private String authorLastName;
    private String email;
    private String phone;
    private String title;
    private String description;
    private Integer price;
    private String image;

}
