package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.AdsCreateDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.mapper.AdsImageMapper;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsImage;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsImageRepository adsImageRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;

    public AdsServiceImpl(
            AdsRepository adsRepository,
            AdsImageRepository adsImageRepository,
            AdsCommentRepository adsCommentRepository,
            UserRepository userRepository
    ) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public AdsDto create(AdsCreateDto adsCreateDto) {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("id: 1"));

        Ads ads = AdsMapper.INSTANCE.adsCreateDtoToAds(adsCreateDto);
        ads.setUser(user);
        ads = adsRepository.save(ads);

        AdsImage adsImage = AdsImageMapper.INSTANCE.adsToAdsImage(ads, adsCreateDto.getImage());
        adsImageRepository.save(adsImage);

        return AdsMapper.INSTANCE.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public AdsDto update(Integer id, AdsDto adsDto) {
        Ads createdAds = adsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        Ads ads = AdsMapper.INSTANCE.adsDtoToAds(adsDto);
        ads.setId(Long.valueOf(id));
        ads.setUser(createdAds.getUser());
        ads.setCreatedAt(createdAds.getCreatedAt());
        ads.setDescription(createdAds.getDescription());
        ads = adsRepository.save(ads);

        AdsImage adsImage = adsImageRepository.findByAdsEquals(ads);

        if (null == adsImage) {
            adsImage = new AdsImage();
            adsImage.setAds(ads);
        }

        adsImage.setImage(adsDto.getImage());
        adsImageRepository.save(adsImage);

        return AdsMapper.INSTANCE.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Ads ads = adsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        adsCommentRepository.deleteByAdsEquals(ads);
        adsImageRepository.deleteByAdsEquals(ads);
        adsRepository.delete(ads);
    }

    @Override
    public AdsFullDto getOne(Integer id) {
        Ads ads = adsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        return AdsMapper.INSTANCE.adsToAdsFullDto(ads);
    }

    @Override
    public ResponseWrapperAdsDto getAll() {
        List<Ads> list = adsRepository.findAll();

        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();

        for (Ads ads : list) {
            responseWrapperAdsDto.setOneDto(AdsMapper.INSTANCE.adsToAdsDto(ads));
        }

        return responseWrapperAdsDto;
    }

    @Override
    public ResponseWrapperAdsDto getAllMine() {
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new EntityNotFoundException("id: 1"));

        List<Ads> list = adsRepository.findByUserEquals(user);

        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();

        for (Ads ads : list) {
            responseWrapperAdsDto.setOneDto(AdsMapper.INSTANCE.adsToAdsDto(ads));
        }

        return responseWrapperAdsDto;
    }

}
