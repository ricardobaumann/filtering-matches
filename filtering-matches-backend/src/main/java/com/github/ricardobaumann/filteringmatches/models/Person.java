package com.github.ricardobaumann.filteringmatches.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    private String id;

    private String displayName;

    private Integer age;

    private String jobTitle;

    private Integer heightInCm;

    private City city;

    private String mainPhoto;

    private Double compatibilityScore;

    private Integer contactsExchanged;

    private Boolean favourite;

    private String religion;
}
