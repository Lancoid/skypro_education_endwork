package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdsCreateDto {

    private Integer pk;
    private String title;
    private String description;
    private String image;
    private Integer price;

}
