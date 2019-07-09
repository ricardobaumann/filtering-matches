package com.github.ricardobaumann.filteringmatches.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

    @JsonProperty("display_name")
    private String displayName;

    private Integer age;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("height_in_cm")
    private Integer heightInCm;

    private CityDto city;

    @JsonProperty("main_photo")
    private String mainPhoto;

    @JsonProperty("compatibility_score")
    private Double compatibilityScore;

    @JsonProperty("contacts_exchanged")
    private Integer contactsExchanged;

    private Boolean favorite;

    private String religion;

}
