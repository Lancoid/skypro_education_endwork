package ru.skypro.homework.service.adsImage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.component.authenticationFacade.AuthenticationFacade;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.mapper.AdsImageMapper;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;
import ru.skypro.homework.repository.AdsRepository;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
public class AdsImageServiceImpl implements AdsImageService {

    private final AdsRepository adsRepository;
    private final AdsImageRepository adsImageRepository;
    private final AuthenticationFacade authenticationFacade;

    public AdsImageServiceImpl(
            AdsRepository adsRepository,
            AdsImageRepository adsImageRepository,
            AuthenticationFacade authenticationFacade
    ) {
        this.adsRepository = adsRepository;
        this.adsImageRepository = adsImageRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public void save(Ads ads, MultipartFile file) throws IOException {
        AdsImage adsImage = new AdsImage();

        adsImage.setFileSize(file.getSize());
        adsImage.setFileName(UUID.randomUUID().toString());
        adsImage.setMediaType(file.getContentType());
        adsImage.setData(file.getBytes());
        adsImage.setUrl("/image/" + adsImage.getFileName());
        adsImage.setAds(ads);

        adsImageRepository.saveAndFlush(adsImage);
    }


    public AdsDto update(Long adsId, MultipartFile file) throws AccessDeniedException {
        Ads ads = adsRepository.findById(adsId)
                .orElseThrow(() -> new EntityNotFoundException("id: " + adsId));

        authenticationFacade.isAdminOrOwner(ads.getUser().getId());

        AdsImage adsImage = adsImageRepository.findByAdsEquals(ads)
                .orElseThrow(() -> new EntityNotFoundException("id: " + adsId));

        try {
            adsImage.setFileSize(file.getSize());
            adsImage.setMediaType(file.getContentType());
            adsImage.setData(file.getBytes());
            adsImage.setUrl("/image/" + adsImage.getFileName());
            adsImage.setAds(ads);

            adsImage = adsImageRepository.saveAndFlush(adsImage);
        } catch (Throwable throwable) {
            return null;
        }

        return AdsImageMapper.INSTANCE.adsImageToAdsDto(adsImage);
    }
}
