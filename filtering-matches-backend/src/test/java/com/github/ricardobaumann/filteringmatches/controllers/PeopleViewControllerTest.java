package com.github.ricardobaumann.filteringmatches.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ricardobaumann.filteringmatches.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatches.dtos.Range;
import com.github.ricardobaumann.filteringmatches.models.City;
import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.service.PeopleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PeopleViewControllerTest {

    @Mock
    private PeopleService peopleService;

    @InjectMocks
    private PeopleViewController peopleViewController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(peopleViewController)
                .build();
    }

    @Test
    void shouldReturnResultArray() throws Exception {
        //Given
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
        List<Person> results = Collections.singletonList(person);
        PersonFilter personFilter = new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232, 51.568535);

        when(peopleService.searchFor(personFilter))
                .thenReturn(results);


        //When //Then
        mockMvc.perform(post("/people/report")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personFilter)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].display_name", is(person.getDisplayName())))
                .andExpect(jsonPath("$[0].age", is(person.getAge())))
                .andExpect(jsonPath("$[0].job_title", is(person.getJobTitle())))
                .andExpect(jsonPath("$[0].city.name", is(person.getCity().getName())))
                .andExpect(jsonPath("$[0].city.lat", is(person.getCity().getPosition().getY())))
                .andExpect(jsonPath("$[0].city.lon", is(person.getCity().getPosition().getY())))
                .andExpect(jsonPath("$[0].main_photo", is(person.getMainPhoto())))
                .andExpect(jsonPath("$[0].compatibility_score", is(person.getCompatibilityScore())))
                .andExpect(jsonPath("$[0].contacts_exchanged", is(person.getContactsExchanged())))
                .andExpect(jsonPath("$[0].favourite", is(person.getFavourite())))
                .andExpect(jsonPath("$[0].religion", is(person.getReligion())))
        ;
    }

    @TestFactory
    public List<DynamicTest> shouldValidateInputField() {

        return Stream.of(new PersonFilter(
                        true,
                        true,
                        true,
                        22.3,
                        new Range(10, 20),
                        new Range(100, 200),
                        null,
                        null,
                        null),
                new PersonFilter(
                        true,
                        true,
                        true,
                        22.3,
                        new Range(10, 20),
                        new Range(100, 200),
                        null,
                        20.0, 20.0),
                new PersonFilter(
                        true,
                        true,
                        true,
                        22.3,
                        new Range(10, 20),
                        new Range(100, 200),
                        new Range(20, 30),
                        null,null),
                new PersonFilter(
                        true,
                        true,
                        true,
                        22.3,
                        new Range(10, 20),
                        new Range(100, 200),
                        new Range(20, null),
                        null,null)
        )
                .map(personFilter -> DynamicTest.dynamicTest("Validate input", () -> {
                    mockMvc.perform(post("/people/report")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(personFilter)))
                            .andExpect(status().isBadRequest());
                })).collect(Collectors.toList());
    }
}
