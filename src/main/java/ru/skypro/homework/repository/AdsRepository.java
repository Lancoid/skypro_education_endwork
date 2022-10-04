package ru.skypro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.skypro.homework.model.Ads;
import ru.skypro.homework.model.User;

import java.util.List;

public interface AdsRepository extends JpaRepository<Ads, Long> {

    List<Ads> findByUserEquals(@NonNull User user);

}
