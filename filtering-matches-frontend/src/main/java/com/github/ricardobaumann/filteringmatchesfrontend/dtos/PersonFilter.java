package com.github.ricardobaumann.filteringmatchesfrontend.dtos;

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

    @NotNull
    private Double compatibilityScore;

    @Valid
    @NotNull
    private Range ageRange;

    @Valid
    @NotNull
    private Range heightRange;

    @NotNull
    private Range distanceInKm;

    @NotNull
    private Double longitude;

    @NotNull
    private Double latitude;

}
