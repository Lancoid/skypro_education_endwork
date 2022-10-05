package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsImage;

@Mapper
public interface AdsImageMapper {

    AdsImageMapper INSTANCE = Mappers.getMapper(AdsImageMapper.class);

    @Mapping(target = "image", source = "image")
    @Mapping(target = "ads", source = "ads")
    AdsImage adsToAdsImage(Ads ads, String image);

}
