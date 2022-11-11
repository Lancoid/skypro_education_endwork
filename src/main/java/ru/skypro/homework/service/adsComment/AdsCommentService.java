package ru.skypro.homework.service.adsComment;

import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;

import java.nio.file.AccessDeniedException;

public interface AdsCommentService {

    AdsCommentDto create(String adPk, AdsCommentDto adsCommentDto);

    AdsCommentDto update(String adPk, Integer id, AdsCommentDto adsCommentDto) throws AccessDeniedException;

    void delete(String adPk, Integer id) throws AccessDeniedException;

    AdsCommentDto getOne(String adPk, Integer id);

    ResponseWrapperAdsCommentDto getAll(String adPk);

}
