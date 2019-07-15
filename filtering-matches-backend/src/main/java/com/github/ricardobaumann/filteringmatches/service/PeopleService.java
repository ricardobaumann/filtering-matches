package com.github.ricardobaumann.filteringmatches.service;

import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.repos.PeopleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public void save(Person person) {
        peopleRepository.save(person);
    }

    public List<Person> searchFor(PersonFilter personFilter) {
        return peopleRepository.findBy(personFilter);
    }
}
