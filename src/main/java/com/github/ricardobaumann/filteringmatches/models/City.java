package com.github.ricardobaumann.filteringmatches.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String name;

    @GeoSpatialIndexed
    private double[] position;
}
