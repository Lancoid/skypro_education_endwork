package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsImage;

public interface AdsImageRepository extends JpaRepository<AdsImage, Long> {

    void deleteByAdsEquals(@NonNull Ads ads);

    AdsImage findByFileNameEqualsIgnoreCase(@NonNull String fileName);

}
