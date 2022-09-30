package ru.skypro.homework.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AdsComment {

    private Integer pk;
    private OffsetDateTime createdAt;
    private Integer author;
    private String text;

}
