package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdsDto {

    private Integer pk;
    private Integer author;
    private String title;
    private Integer price;
    private String image;

}
