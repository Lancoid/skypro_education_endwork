package ru.skypro.homework.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class AdsCreateDto {

    private Integer pk;
    private String title;
    private String description;
    private MultipartFile image;
    private Integer price;

}
