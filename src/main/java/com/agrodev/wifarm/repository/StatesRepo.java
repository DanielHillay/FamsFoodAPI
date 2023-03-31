package com.agrodev.wifarm.repository;

import com.agrodev.wifarm.entity.States;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatesRepo extends JpaRepository<States, Long> {
    List<States> findByCountryName(String country);
}
