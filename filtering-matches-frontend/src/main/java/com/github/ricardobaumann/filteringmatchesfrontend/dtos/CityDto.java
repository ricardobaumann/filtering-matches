package com.github.ricardobaumann.filteringmatchesfrontend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDto {
    private String name;

    private Double lat;

    private Double lon;
}
