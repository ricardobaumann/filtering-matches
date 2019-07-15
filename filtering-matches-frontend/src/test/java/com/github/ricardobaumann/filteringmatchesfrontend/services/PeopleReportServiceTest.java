package com.github.ricardobaumann.filteringmatchesfrontend.services;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.Range;
import com.github.ricardobaumann.filteringmatchesfrontend.repos.PeopleReportRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PeopleReportServiceTest {

    @Mock
    private PeopleReportRepo peopleReportRepo;

    @InjectMocks
    private PeopleReportService peopleReportService;

    @Test
    void shouldFilterWithRepo() {
        //Given
        PersonFilter personFilter = new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232, 51.568535);
        List<PersonDto> results = Collections.singletonList(new PersonDto());

        //When
        when(peopleReportRepo.createReport(personFilter))
                .thenReturn(results);

        //Then
        assertThat(peopleReportService.filter(personFilter))
                .isEqualTo(results);
    }
}
