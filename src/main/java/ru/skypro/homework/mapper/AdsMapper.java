package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsCreateDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.model.Ads;

@Mapper
public interface AdsMapper {

    AdsMapper INSTANCE = Mappers.getMapper(AdsMapper.class);

    @Mapping(target = "image", source = "adsImage.image")
    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    AdsDto adsToAdsDto(Ads ads);

    @Mapping(target = "user.id", source = "author")
    @Mapping(target = "id", source = "pk")
    Ads adsDtoToAds(AdsDto ads);

    @Mapping(target = "pk", source = "id")
    AdsCreateDto adsToAdsCreateDto(Ads ads);

    @Mapping(target = "id", source = "pk")
    Ads adsCreateDtoToAds(AdsCreateDto ads);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "image", source = "adsImage.image")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phone", source = "user.phone")
    @Mapping(target = "authorLastName", source = "user.lastName")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    AdsFullDto adsToAdsFullDto(Ads ads);

}
