package ru.skypro.homework.service.ads;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AdsCreateDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public interface AdsService {

    AdsDto create(AdsCreateDto adsCreateDto, MultipartFile file) throws IOException;

    AdsDto update(Integer id, AdsDto adsDto) throws AccessDeniedException;

    void delete(Integer id) throws AccessDeniedException;

    AdsFullDto getOne(Integer id) throws AccessDeniedException;

    ResponseWrapperAdsDto getAll();

    ResponseWrapperAdsDto getAllMine();

}
