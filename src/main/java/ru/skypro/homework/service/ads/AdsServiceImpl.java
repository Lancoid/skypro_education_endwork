package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.component.AuthenticationFacade;
import ru.skypro.homework.dto.AdsCreateDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;
import ru.skypro.homework.mapper.AdsMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.AdsCommentRepository;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.adsImage.AdsImageService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    private final AdsRepository adsRepository;
    private final AdsImageRepository adsImageRepository;
    private final AdsCommentRepository adsCommentRepository;
    private final UserRepository userRepository;
    private final AuthenticationFacade authenticationFacade;
    private final AdsImageService adsImageService;

    public AdsServiceImpl(
            AdsRepository adsRepository,
            AdsImageRepository adsImageRepository,
            AdsCommentRepository adsCommentRepository,
            UserRepository userRepository,
            AdsImageService adsImageService,
            AuthenticationFacade authenticationFacade
    ) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
        this.adsCommentRepository = adsCommentRepository;
        this.userRepository = userRepository;
        this.adsImageService = adsImageService;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    @Transactional
    public AdsDto create(AdsCreateDto adsCreateDto, MultipartFile file) throws IOException {
        Ads ads = AdsMapper.INSTANCE.adsCreateDtoToAds(adsCreateDto);

        Long userId = authenticationFacade.getUserId();

        if (null == userId) {
            throw new RuntimeException("Ошибка определения пользователя");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("id: " + userId));

        ads.setUser(user);
        ads = adsRepository.save(ads);

        adsImageService.save(ads, file);

        return AdsMapper.INSTANCE.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public AdsDto update(Integer id, AdsDto adsDto) throws AccessDeniedException {
        Ads createdAds = adsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        authenticationFacade.isAdminOrOwner(createdAds.getUser().getId());

        Ads ads = AdsMapper.INSTANCE.adsDtoToAds(adsDto);
        ads.setId(Long.valueOf(id));
        ads.setUser(createdAds.getUser());
        ads.setCreatedAt(createdAds.getCreatedAt());
        ads.setDescription(createdAds.getDescription());
        ads = adsRepository.save(ads);

        return AdsMapper.INSTANCE.adsToAdsDto(ads);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws AccessDeniedException {
        Ads ads = adsRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("id: " + id));

        authenticationFacade.isAdminOrOwner(ads.getUser().getId());

        adsCommentRepository.deleteByAdsEquals(ads);
        adsImageRepository.deleteByAdsEquals(ads);
        adsRepository.deleteById(Long.valueOf(id));
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
        Long userId = authenticationFacade.getUserId();

        if (null == userId) {
            throw new RuntimeException("Ошибка определения пользователя");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("id: " + userId));

        List<Ads> list = adsRepository.findByUserEquals(user);

        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();

        for (Ads ads : list) {
            responseWrapperAdsDto.setOneDto(AdsMapper.INSTANCE.adsToAdsDto(ads));
        }

        return responseWrapperAdsDto;
    }

}
