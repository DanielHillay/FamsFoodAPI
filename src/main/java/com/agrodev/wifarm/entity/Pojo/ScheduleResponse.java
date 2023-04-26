package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.FeedingScheme;
import com.agrodev.wifarm.entity.MealSchedule;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class ScheduleResponse {

    private String userId;
    private MealSchedule mealSchedule;
    private List<FeedingScheme> days;
}
