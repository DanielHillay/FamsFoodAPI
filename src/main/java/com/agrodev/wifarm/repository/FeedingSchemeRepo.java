package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.FeedingScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedingSchemeRepo extends JpaRepository<FeedingScheme, Long> {
    List<FeedingScheme> findAllByScheduleId(String scheduleId);

    List<FeedingScheme> findByWeekDay(String weekDay);

    List<FeedingScheme> findByWeekDayAndTownId(String weekDay, Long town);

    List<FeedingScheme> findByWeekDayAndCountry(String weekDay, String country);


    List<FeedingScheme> findByWeekDayAndLGA(String weekDay, String lga);

    List<FeedingScheme> findByWeekDayAndState(String weekDay, String state);
}
