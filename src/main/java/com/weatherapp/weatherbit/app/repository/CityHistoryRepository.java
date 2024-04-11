package com.weatherapp.weatherbit.app.repository;

import com.weatherapp.weatherbit.app.entity.CityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityHistoryRepository extends JpaRepository<CityHistory, Long> {
}