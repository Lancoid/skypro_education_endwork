package ru.skypro.homework.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseWrapperAdsCommentDto {

    private Integer count = 0;
    private List<AdsCommentDto> results = new ArrayList<>();

    public void setOneDto(@NotNull AdsCommentDto adsCommentDto) {
        ++this.count;
        this.results.add(adsCommentDto);
    }

    public void setManyDto(@NotNull List<AdsCommentDto> results) {
        this.count = results.size();
        this.results = results;
    }

}
