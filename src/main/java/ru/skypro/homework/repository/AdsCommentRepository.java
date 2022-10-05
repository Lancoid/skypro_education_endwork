package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.AdsComment;

import java.util.List;

public interface AdsCommentRepository extends JpaRepository<AdsComment, Long> {

    List<AdsComment> findByAdsEquals(@NonNull Ads ads);

    void deleteByAdsEquals(@NonNull Ads ads);

}
