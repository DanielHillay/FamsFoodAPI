package com.agrodev.wifarm.entity.Pojo;

import com.agrodev.wifarm.entity.MealSchedule;
import com.agrodev.wifarm.entity.MealTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Data
public class ScheduleRequest {

    private String requestId;
    private MealSchedule schedule;
    private List<List<MealTime>> timeList;
}
