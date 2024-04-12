package com.weatherapp.weatherbit.app.repository;

import com.weatherapp.weatherbit.app.entity.CityHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityHistoryRepository extends JpaRepository<CityHistory, Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT city_name, time FROM city_history WHERE user_id = :userId AND city_name IS NOT NULL ORDER BY time DESC"
    )
    List<String> getCitiesQueriedByUserId(@Param("userId") Long userId);

}