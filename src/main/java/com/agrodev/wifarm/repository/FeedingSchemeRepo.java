package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.FeedingScheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedingSchemeRepo extends JpaRepository<FeedingScheme, Long> {
    List<FeedingScheme> findAllByScheduleId(String scheduleId);

    List<FeedingScheme> findByWeekDay(String weekDay);
}
