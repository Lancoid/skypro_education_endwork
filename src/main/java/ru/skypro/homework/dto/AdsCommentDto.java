package ru.skypro.homework.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AdsCommentDto {

    private Integer pk;
    private OffsetDateTime createdAt;
    private Integer author;
    private String text;

}
