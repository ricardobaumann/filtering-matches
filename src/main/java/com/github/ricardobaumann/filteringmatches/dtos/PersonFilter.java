package com.github.ricardobaumann.filteringmatches.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonFilter {

    private Boolean hasPhoto;

    private Boolean inContact;

    private Boolean favorite;

    private Double compatibilityScore;

    @Valid
    private Range ageRange;

    @Valid
    private Range heightRange;

    @NotNull
    private Range distanceInKm;

    @NotNull
    private double[] coordinates;

}
