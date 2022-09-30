package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public Ads create(CreateAds createAds) {
        return null;
    }

    @Override
    public Ads update(Integer id, Ads createAds) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public FullAds getOne(Integer id) {
        return null;
    }

    @Override
    public ResponseWrapperAds getAll() {
        return null;
    }

    @Override
    public ResponseWrapperAds getAllMine() {
        return null;
    }

}
