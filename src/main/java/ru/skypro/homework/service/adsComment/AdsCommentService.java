package ru.skypro.homework.service.adsComment;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;

public interface AdsCommentService {

    AdsCommentDto create(String adPk, AdsCommentDto adsCommentDto);

    AdsCommentDto update(String adPk, Integer id, AdsCommentDto adsCommentDto);

    void delete(String adPk, Integer id);

    AdsCommentDto getOne(String adPk, Integer id);

    ResponseWrapperAdsCommentDto getAll(String adPk);

}
