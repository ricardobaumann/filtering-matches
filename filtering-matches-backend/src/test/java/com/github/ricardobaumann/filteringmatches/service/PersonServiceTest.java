package com.github.ricardobaumann.filteringmatches.service;

import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.dtos.Range;
import com.github.ricardobaumann.filteringmatches.models.City;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.repos.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    void shouldSavePerson() {
        //Given
        Person person = new Person();
        when(personRepository.save(person))
                .thenReturn(person);

        //When
        personService.save(person);

        //Then
        verify(personRepository).save(person);
    }

    @Test
    void shouldSearchFor() {
        //Given
        PersonFilter personFilter = new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                new double[]{-1.772232, 51.568535});

        Person person = new Person(
                null,
                "display name",
                18,
                "job title",
                183,
                new City("city", new GeoJsonPoint(20, 20)),
                "photo",
                22.3,
                2,
                true,
                "love"

        );

        when(personRepository.findBy(personFilter))
                .thenReturn(Collections.singletonList(person));

        //When
        assertThat(personService.searchFor(personFilter))
                .containsExactly(person);
    }
}
