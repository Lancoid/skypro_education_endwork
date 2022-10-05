package ru.skypro.homework.service.adsComment;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsCommentDto;
import ru.skypro.homework.dto.ResponseWrapperAdsCommentDto;
import ru.skypro.homework.mapper.AdsCommentMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsComment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AdsCommentServiceImpl implements AdsCommentService {

    private final AdsRepository adsRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;

    public AdsCommentServiceImpl(
            AdsRepository adsRepository,
            AdsCommentRepository adsCommentRepository,
            UserRepository userRepository
    ) {
        this.adsRepository = adsRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public AdsCommentDto create(String adPk, AdsCommentDto adsCommentDto) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("id: 1"));

        Ads createdAds = adsRepository.findById(Long.valueOf(adPk))
                .orElseThrow(() -> new EntityNotFoundException("id: " + adPk));

        AdsComment adsComment = AdsCommentMapper.INSTANCE.adsCommentDtoToAdsComment(adsCommentDto);
        adsComment.setAds(createdAds);
        adsComment.setUser(user);

        adsComment = adsCommentRepository.save(adsComment);

        return AdsCommentMapper.INSTANCE.adsCommentToAdsCommentDto(adsComment);
    }

    @Override
    public AdsCommentDto update(String adPk, Integer id, AdsCommentDto adsCommentDto) {
        AdsComment adsComment = adsCommentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        adsComment.setText(adsCommentDto.getText());

        adsCommentRepository.save(adsComment);

        return AdsCommentMapper.INSTANCE.adsCommentToAdsCommentDto(adsComment);
    }

    @Override
    public void delete(String adPk, Integer id) {
        AdsComment adsComment = adsCommentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        adsCommentRepository.delete(adsComment);
    }

    @Override
    public AdsCommentDto getOne(String adPk, Integer id) {
        AdsComment adsComment = adsCommentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        return AdsCommentMapper.INSTANCE.adsCommentToAdsCommentDto(adsComment);
    }

    @Override
    public ResponseWrapperAdsCommentDto getAll(String adPk) {
        Ads ads = adsRepository.findById(Long.valueOf(adPk))
                .orElseThrow(() -> new EntityNotFoundException("id: " + adPk));

        List<AdsComment> list = adsCommentRepository.findByAdsEquals(ads);


        ResponseWrapperAdsCommentDto responseWrapperAdsCommentDto = new ResponseWrapperAdsCommentDto();

        for (AdsComment adsComment : list) {
            responseWrapperAdsCommentDto.setOneDto(AdsCommentMapper.INSTANCE.adsCommentToAdsCommentDto(adsComment));
        }

        return responseWrapperAdsCommentDto;
    }

}
