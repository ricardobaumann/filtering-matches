package com.github.ricardobaumann.filteringmatches.repos;

import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.dtos.Range;
import com.github.ricardobaumann.filteringmatches.models.City;
import com.github.ricardobaumann.filteringmatches.models.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PeopleRepositoryTest {

    @Autowired
    private PeopleRepository peopleRepository;

    @Test
    void shouldReturnValuesWhenPresent() {
        //Given
        peopleRepository.deleteAll();
        peopleRepository.save(
                new Person(
                        null,
                        "display name",
                        18,
                        "job title",
                        183,
                        new City("city", new GeoJsonPoint(-0.118092, 51.509865)),
                        "photo",
                        22.3,
                        10,
                        true,
                        "love"

                )
        );

        //When
        List<Person> results = peopleRepository.findBy(new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232, 51.568535
        ));

        //Then
        assertThat(results).isNotEmpty();
    }

    @Test
    void shouldReturnNoneWhenNoMatches() {
        //Given
        peopleRepository.deleteAll();
        peopleRepository.save(
                new Person(
                        null,
                        "display name",
                        18,
                        "job title",
                        183,
                        new City("city", new GeoJsonPoint(-0.118092, 51.509865)),
                        "photo",
                        22.3,
                        10,
                        true,
                        "love"

                )
        );

        //When
        List<Person> results = peopleRepository.findBy(new PersonFilter(
                true,
                true,
                false,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232, 51.568535
        ));

        //Then
        assertThat(results).isEmpty();
    }
}
