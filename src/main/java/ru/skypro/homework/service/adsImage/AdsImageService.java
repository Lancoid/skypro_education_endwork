package ru.skypro.homework.service.adsImage;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.model.Ads;

import java.io.IOException;

public interface AdsImageService {

    void save(Ads ads, MultipartFile file) throws IOException;

    AdsDto update(Long adsId, MultipartFile file);

}
