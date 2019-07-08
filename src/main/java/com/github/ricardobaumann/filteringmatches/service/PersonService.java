package com.github.ricardobaumann.filteringmatches.service;

import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.repos.PersonRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void save(Person person) {
        personRepository.save(person);
    }
}
