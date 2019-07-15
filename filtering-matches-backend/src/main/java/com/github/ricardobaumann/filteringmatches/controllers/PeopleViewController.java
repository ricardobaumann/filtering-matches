package com.github.ricardobaumann.filteringmatches.controllers;

import com.github.ricardobaumann.filteringmatches.dtos.CityDto;
import com.github.ricardobaumann.filteringmatches.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.service.PeopleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
public class PeopleViewController {

    private final PeopleService peopleService;

    public PeopleViewController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @PostMapping("/people/report")
    public List<PersonDto> post(@Valid @RequestBody PersonFilter personFilter) {
        return peopleService.searchFor(personFilter)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private PersonDto toDto(Person person) {
        return new PersonDto(
                person.getDisplayName(),
                person.getAge(),
                person.getJobTitle(),
                person.getHeightInCm(),
                new CityDto(person.getCity().getName(), person.getCity().getPosition().getX(), person.getCity().getPosition().getY()),
                person.getMainPhoto(),
                person.getCompatibilityScore(),
                person.getContactsExchanged(),
                person.getFavourite(),
                person.getReligion()
        );
    }
}
