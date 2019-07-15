package com.github.ricardobaumann.filteringmatchesfrontend.controller;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.CityDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.Range;
import com.github.ricardobaumann.filteringmatchesfrontend.services.PeopleReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class PeopleControllerTest {

    @Mock
    private PeopleReportService peopleReportService;

    @InjectMocks
    private PeopleController peopleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(peopleController)
                .build();
    }

    @Test
    void shouldRenderFilterFormOnIndex() throws Exception {

        mockMvc.perform(get("/index"))
                .andExpect(model().attribute("personFilter", instanceOf(PersonFilter.class)))
                .andExpect(view().name("filter-form"));

    }

    @Test
    void shouldRenderResultsOnSuccessfulFilter() throws Exception {
        //Given
        ArgumentCaptor<PersonFilter> filterArgumentCaptor = ArgumentCaptor.forClass(PersonFilter.class);

        List<PersonDto> results = Collections.singletonList(new PersonDto(
                "person 1", 20, "job 1", 120, new CityDto("gotham", 0.0, 0.0),
                "photo", 1.0, 2, true, "love"
        ));

        when(peopleReportService.filter(any()))
                .thenReturn(results);

        //When //Then
        mockMvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("hasPhoto=true&_hasPhoto=on&inContact=true&_inContact=on&favourite=true" +
                        "&_favourite=on&compatibilityScore=22.3&ageRange.from=10" +
                        "&ageRange.to=20&heightRange.from=100&heightRange.to=200&distanceInKm.from=100" +
                        "&distanceInKm.to=500&longitude=-1.772232&latitude=51.568535"))
                .andExpect(model().attribute("people", is(results)));

        verify(peopleReportService).filter(filterArgumentCaptor.capture());
        assertThat(filterArgumentCaptor.getValue()).isEqualTo(new PersonFilter(
                true,
                true,
                true,
                22.3,
                new Range(10, 20),
                new Range(100, 200),
                new Range(100, 500),
                -1.772232, 51.568535));
    }

    @Test
    public void shouldRenderFilterFormOnFilterValidationFail() throws Exception {
        mockMvc.perform(post("/filter")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content("hasPhoto=true"))
                .andExpect(view().name("filter-form"))
                .andExpect(model().attribute("org.springframework.validation.BindingResult.personFilter",
                        instanceOf(BeanPropertyBindingResult.class)));
    }
}
