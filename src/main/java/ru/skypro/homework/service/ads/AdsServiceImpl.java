package ru.skypro.homework.service.ads;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.CreateAdsDto;
import ru.skypro.homework.dto.FullAdsDto;
import ru.skypro.homework.dto.ResponseWrapperAdsDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdsServiceImpl implements AdsService {

    @Override
    public AdsDto create(CreateAdsDto createAdsDto) {
        return getAds();
    }

    @Override
    public AdsDto update(Integer id, AdsDto adsDto) {
        return getAds();
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public FullAdsDto getOne(Integer id) {
        FullAdsDto fullAdsDto = new FullAdsDto();

        fullAdsDto.setPk(0);

        fullAdsDto.setAuthorLastName("authorLastName");
        fullAdsDto.setAuthorFirstName("authorFirstName");
        fullAdsDto.setEmail("email");
        fullAdsDto.setPhone("phone");
        fullAdsDto.setTitle("title");
        fullAdsDto.setImage("image");
        fullAdsDto.setDescription("description");
        fullAdsDto.setPrice(5);

        return fullAdsDto;
    }

    @Override
    public ResponseWrapperAdsDto getAll() {
        List<AdsDto> list = new ArrayList<>();
        list.add(getAds());

        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(list.size());
        responseWrapperAdsDto.setResults(list);

        return responseWrapperAdsDto;
    }

    @Override
    public ResponseWrapperAdsDto getAllMine() {
        List<AdsDto> list = new ArrayList<>();
        list.add(getAds());

        ResponseWrapperAdsDto responseWrapperAdsDto = new ResponseWrapperAdsDto();
        responseWrapperAdsDto.setCount(list.size());
        responseWrapperAdsDto.setResults(list);

        return responseWrapperAdsDto;
    }

    private AdsDto getAds() {
        AdsDto adsDto = new AdsDto();

        adsDto.setPk(1);
        adsDto.setTitle("title");
        adsDto.setImage("image");
        adsDto.setAuthor(6);
        adsDto.setPrice(5);

        return adsDto;
    }

}
