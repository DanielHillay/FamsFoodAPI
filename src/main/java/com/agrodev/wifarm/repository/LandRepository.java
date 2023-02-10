package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandRepository extends JpaRepository<Land, Long> {
    List<Land> findByLandState(String landState);
}
