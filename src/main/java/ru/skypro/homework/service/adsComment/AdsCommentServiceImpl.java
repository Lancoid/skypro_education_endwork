package ru.skypro.homework.service.adsComment;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsComment;
import ru.skypro.homework.dto.ResponseWrapperAdsComment;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdsCommentServiceImpl implements AdsCommentService {

    @Override
    public AdsComment create(String adPk, AdsComment comment) {
        return getAdsComment();
    }

    @Override
    public AdsComment update(String adPk, Integer id, AdsComment comment) {
        return getAdsComment();
    }

    @Override
    public void delete(String adPk, Integer id) {

    }

    @Override
    public AdsComment getOne(String adPk, Integer id) {
        return getAdsComment();
    }

    @Override
    public ResponseWrapperAdsComment getAll(String adPk) {
        List<AdsComment> list = new ArrayList<>();
        list.add(getAdsComment());

        ResponseWrapperAdsComment responseWrapperAdsComment = new ResponseWrapperAdsComment();
        responseWrapperAdsComment.setCount(list.size());
        responseWrapperAdsComment.setResults(list);

        return responseWrapperAdsComment;
    }

    private AdsComment getAdsComment() {
        AdsComment adsComment = new AdsComment();

        adsComment.setCreatedAt(OffsetDateTime.parse("2000-01-23T04:56:07.000+00:00"));
        adsComment.setAuthor(6);
        adsComment.setPk(1);
        adsComment.setText("text");

        return adsComment;
    }

}
