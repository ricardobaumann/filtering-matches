package com.github.ricardobaumann.filteringmatches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFilter {

    private Boolean hasPhoto;

    private Boolean inContact;

    private String favorite;

    private Integer compatibilityScore;

    private Range ageRange;

    private Range heightRange;

    private Range distanceInKm;

}