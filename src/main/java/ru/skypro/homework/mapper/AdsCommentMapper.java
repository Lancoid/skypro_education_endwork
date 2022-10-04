package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.model.AdsComment;

@Mapper
public interface AdsCommentMapper {

    AdsCommentMapper INSTANCE = Mappers.getMapper(AdsCommentMapper.class);

    @Mapping(target = "pk", source = "id")
    @Mapping(target = "author", source = "user.id")
    AdsCommentDto adsCommentToAdsCommentDto(AdsComment adsComment);

    @Mapping(target = "id", source = "pk")
    AdsComment adsCommentDtoToAdsComment(AdsCommentDto adsCommentDto);

}
