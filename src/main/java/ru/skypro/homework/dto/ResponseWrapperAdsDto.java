package ru.skypro.homework.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseWrapperAdsDto {

    private int count = 0;
    private List<AdsDto> results = new ArrayList<>();

    public void setOneDto(@NotNull AdsDto adsDto) {
        ++this.count;
        this.results.add(adsDto);
    }

    public void setManyDto(@NotNull List<AdsDto> results) {
        this.count = results.size();
        this.results = results;
    }

}
