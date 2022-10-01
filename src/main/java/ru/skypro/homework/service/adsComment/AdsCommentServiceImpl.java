package ru.skypro.homework.service.adsComment;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdsCommentServiceImpl implements AdsCommentService {

    @Override
    public AdsCommentDto create(String adPk, AdsCommentDto adsCommentDto) {
        return getAdsComment();
    }

    @Override
    public AdsCommentDto update(String adPk, Integer id, AdsCommentDto adsCommentDto) {
        return getAdsComment();
    }

    @Override
    public void delete(String adPk, Integer id) {

    }

    @Override
    public AdsCommentDto getOne(String adPk, Integer id) {
        return getAdsComment();
    }

    @Override
    public ResponseWrapperAdsCommentDto getAll(String adPk) {
        List<AdsCommentDto> list = new ArrayList<>();
        list.add(getAdsComment());

        ResponseWrapperAdsCommentDto responseWrapperAdsCommentDto = new ResponseWrapperAdsCommentDto();
        responseWrapperAdsCommentDto.setCount(list.size());
        responseWrapperAdsCommentDto.setResults(list);

        return responseWrapperAdsCommentDto;
    }

    private AdsCommentDto getAdsComment() {
        AdsCommentDto adsCommentDto = new AdsCommentDto();

        adsCommentDto.setCreatedAt(OffsetDateTime.parse("2000-01-23T04:56:07.000+00:00"));
        adsCommentDto.setAuthor(6);
        adsCommentDto.setPk(1);
        adsCommentDto.setText("text");

        return adsCommentDto;
    }

}
