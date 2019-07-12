package com.github.ricardobaumann.filteringmatchesfrontend.controller;

import com.github.ricardobaumann.filteringmatchesfrontend.dtos.CityDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonDto;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.PersonFilter;
import com.github.ricardobaumann.filteringmatchesfrontend.dtos.Range;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Slf4j
@Controller
public class PeopleController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("personFilter", new PersonFilter(
                true,
                true,
                true,
                0.0,
                new Range(0, 100),
                new Range(0, 200),
                new Range(0, 500),
                new double[]{0.0, 0.0}
        ));
        return "filter-form";
    }

    @PostMapping("/filter")
    public String filter(Model model, PersonFilter personFilter) {

        log.info("Filtering with {}", personFilter);

        model.addAttribute("people", Arrays.asList(
                new PersonDto(
                        "display name 1",
                        18,
                        "job title 1",
                        183,
                        new CityDto("mario city", 20.0, 12.0),
                        "mainPhoto",
                        12.2,
                        2,
                        true,
                        "love"
                ),
                new PersonDto(
                        "display name 2",
                        30,
                        "job title 2",
                        200,
                        new CityDto("gotham city", 23.0, 99.0),
                        "mainPhoto 2",
                        12.2,
                        2,
                        true,
                        "peace"
                )
        ));

        return "result";
    }

}
