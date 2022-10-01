package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateAds;
import ru.skypro.homework.dto.FullAds;
import ru.skypro.homework.dto.ResponseWrapperAds;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public Ads create(CreateAds createAds) {
        return getAds();
    }

    @Override
    public Ads update(Integer id, Ads createAds) {
        return getAds();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public FullAds getOne(Integer id) {
        FullAds fullAds = new FullAds();

        fullAds.setPk(0);

        fullAds.setAuthorLastName("authorLastName");
        fullAds.setAuthorFirstName("authorFirstName");
        fullAds.setEmail("email");
        fullAds.setPhone("phone");
        fullAds.setTitle("title");
        fullAds.setImage("image");
        fullAds.setDescription("description");
        fullAds.setPrice(5);

        return fullAds;
    }

    @Override
    public ResponseWrapperAds getAll() {
        List<Ads> list = new ArrayList<>();
        list.add(getAds());

        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(list.size());
        responseWrapperAds.setResults(list);

        return responseWrapperAds;
    }

    @Override
    public ResponseWrapperAds getAllMine() {
        List<Ads> list = new ArrayList<>();
        list.add(getAds());

        ResponseWrapperAds responseWrapperAds = new ResponseWrapperAds();
        responseWrapperAds.setCount(list.size());
        responseWrapperAds.setResults(list);

        return responseWrapperAds;
    }

    private Ads getAds() {
        Ads ads = new Ads();

        ads.setPk(1);
        ads.setTitle("title");
        ads.setImage("image");
        ads.setAuthor(6);
        ads.setPrice(5);

        return ads;
    }

}
