package ru.skypro.homework.service.ads;

import ru.skypro.homework.dto.AdsCreateDto;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.AdsFullDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

public interface AdsService {

    AdsDto create(AdsCreateDto adsCreateDto);

    AdsDto update(Integer id, AdsDto adsDto);

    void delete(Integer id);

    AdsFullDto getOne(Integer id);

    ResponseWrapperAdsDto getAll();

    ResponseWrapperAdsDto getAllMine();

}
