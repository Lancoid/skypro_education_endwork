package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AdsCreateDto {

    private String title;
    private String description;
    private Integer price;

}
