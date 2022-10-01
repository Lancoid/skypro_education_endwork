package ru.skypro.homework.service.ads;

import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

public interface AdsService {

    AdsDto create(CreateAdsDto createAdsDto);

    AdsDto update(Integer id, AdsDto adsDto);

    void delete(Integer id);

    FullAdsDto getOne(Integer id);

    ResponseWrapperAdsDto getAll();

    ResponseWrapperAdsDto getAllMine();

}
