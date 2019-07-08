package com.github.ricardobaumann.filteringmatches.service;

import com.github.ricardobaumann.filteringmatches.models.Person;
import com.github.ricardobaumann.filteringmatches.repos.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
    }

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
}
