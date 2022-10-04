package ru.skypro.homework.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseWrapperUserDto {

    private Integer count = 0;
    private List<UserDto> results = new ArrayList<>();

    public void setOneDto(@NotNull UserDto userDto) {
        ++this.count;
        this.results.add(userDto);
    }

    public void setManyDto(@NotNull List<UserDto> results) {
        this.count = results.size();
        this.results = results;
    }

}
