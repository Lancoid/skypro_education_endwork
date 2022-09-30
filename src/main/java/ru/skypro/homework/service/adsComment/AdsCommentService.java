package ru.skypro.homework.service.adsComment;

import ru.skypro.homework.dto.AdsComment;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;

public interface AdsCommentService {

    AdsComment create(String adPk, AdsComment comment);

    AdsComment update(String adPk, Integer id, AdsComment comment);

    void delete(String adPk, Integer id);

    AdsComment getOne(String adPk, Integer id);

    ResponseWrapperAdsComment getAll(String adPk);

}
