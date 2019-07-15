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

    @NotNull
    private Boolean hasPhoto;

    @NotNull
    private Boolean inContact;

    @NotNull
    private Boolean favourite;

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
