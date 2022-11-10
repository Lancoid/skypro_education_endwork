package ru.skypro.homework.service.adsImage;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsImage;
import ru.skypro.homework.repository.AdsImageRepository;

import java.io.IOException;
import java.util.UUID;

@Service
public class AdsImageServiceImpl implements AdsImageService {

    private final AdsImageRepository adsImageRepository;

    public AdsImageServiceImpl(
            AdsImageRepository adsImageRepository
    ) {
        this.adsImageRepository = adsImageRepository;
    }

    @Override
    public void save(Ads ads, MultipartFile file) throws IOException {
        AdsImage adsImage = new AdsImage();

        adsImage.setFileSize(file.getSize());
        adsImage.setFileName(UUID.randomUUID().toString());
        adsImage.setMediaType(file.getContentType());
        adsImage.setData(file.getBytes());
        adsImage.setUrl("/static/" + adsImage.getFileName());
        adsImage.setAds(ads);

        adsImageRepository.saveAndFlush(adsImage);
    }

}
