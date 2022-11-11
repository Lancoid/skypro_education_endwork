package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.AdsImage;

@Mapper
public interface AdsImageMapper {

    AdsImageMapper INSTANCE = Mappers.getMapper(AdsImageMapper.class);

    @Mapping(target = "title", source = "ads.title")
    @Mapping(target = "price", source = "ads.price")
    @Mapping(target = "pk", source = "ads.id")
    @Mapping(target = "image", source = "url")
    @Mapping(target = "author", source = "ads.user.id")
    AdsDto adsImageToAdsDto(AdsImage ads);

}
