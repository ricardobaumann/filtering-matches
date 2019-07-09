package com.github.ricardobaumann.filteringmatches.controllers;

import com.github.ricardobaumann.filteringmatches.dtos.CityDto;
import com.github.ricardobaumann.filteringmatches.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.service.PersonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PeopleViewController {

    private final PersonService personService;

    public PeopleViewController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/people/report")
    public List<PersonDto> post(@RequestBody PersonFilter personFilter) {
        return personService.searchFor(personFilter)
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
                new CityDto(person.getCity().getName(), person.getCity().getPosition()[0], person.getCity().getPosition()[1]),
                person.getMainPhoto(),
                person.getCompatibilityScore(),
                person.getContactsExchanged(),
                person.getFavorite(),
                person.getReligion()
        );
    }
}
