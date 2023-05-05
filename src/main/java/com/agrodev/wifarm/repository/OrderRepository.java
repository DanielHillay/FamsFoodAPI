package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.MealOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<MealOrder, Long> {

    List<MealOrder> findByIsAttendedTo(boolean b);

    List<MealOrder> findByUserId(String userId);

    List<MealOrder> findByIsDelivered(boolean b);

    List<MealOrder> findByIsAttendedToAndState(boolean b, String filter);

    List<MealOrder> findByIsAttendedToAndLGA(boolean b, String filter);

    List<MealOrder> findByIsAttendedToAndTown(boolean b, String filter);

    List<MealOrder> findByIsAttendedToAndCountry(boolean b, String filter);
}
