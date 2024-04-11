package com.weatherapp.weatherbit.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityHistory {

    @Id
    @GeneratedValue(
            generator = "city_history_sequence",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "city_history_sequence",
            allocationSize = 10,
            sequenceName = "city_history_sequence"
    )
    private Long id;
    private String cityName;
    private LocalDateTime time;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "userId"
//            nullable = false
    )
    private User user;

}