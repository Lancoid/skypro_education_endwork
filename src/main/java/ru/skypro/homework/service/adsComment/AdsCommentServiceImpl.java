package ru.skypro.homework.service.adsComment;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsComment;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;

@Service
public class AdsCommentServiceImpl implements AdsCommentService {

    @Override
    public AdsComment create(String adPk, AdsComment comment) {
        return null;
    }

    @Override
    public AdsComment update(String adPk, Integer id, AdsComment comment) {
        return null;
    }

    @Override
    public void delete(String adPk, Integer id) {

    }

    @Override
    public AdsComment getOne(String adPk, Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperAdsComment getAll(String adPk) {
        return null;
    }
}
