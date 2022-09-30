package ru.skypro.homework.service.ads;

import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;

public interface AdsService {

    Ads create(CreateAds createAds);

    Ads update(Integer id, Ads createAds);

    void delete(Integer id);

    FullAds getOne(Integer id);

    ResponseWrapperAds getAll();

    ResponseWrapperAds getAllMine();

}
